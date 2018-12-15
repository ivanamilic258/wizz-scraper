package com;

import com.datamodel.Month;
import com.datamodel.WizzDeparturesDestinations;
import com.service.AirportsService;
import com.service.MyMailService;
import com.service.dto.BestDealDto;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.*;

public class Belgrade {


    public static void main(String[] args) throws InterruptedException {

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-configuration.xml");
        AirportsService airportsService =
                (AirportsService) ctx.getBean("airportsService");
        MyMailService myMailService =
                (MyMailService) ctx.getBean("myMailService");


        System.setProperty("webdriver.gecko.driver", "E:\\webdriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();


        List<Month> monthsUntilTheEndOfTheYear = airportsService.getMonthsUntilTheEndOfTheYear();
        List<WizzDeparturesDestinations> departuresAndDestinations = airportsService.findDeparturesAndDestinations();


        airportsService.clearAllPrices();
        for (WizzDeparturesDestinations ddRow : departuresAndDestinations) {

            driver.get("http://wizzair.com/en-gb/flights/timetable/");

            Thread.sleep(5000);
            driver.findElement(By.id("search-departure-station")).sendKeys(ddRow.getDeparture().getName());
            driver.findElement(By.id("search-departure-station")).sendKeys(Keys.ENTER);

            Thread.sleep(5000);
            driver.findElement(By.id("search-arrival-station")).sendKeys(ddRow.getDestination().getName());
            driver.findElement(By.id("search-arrival-station")).sendKeys(Keys.ENTER);

            Thread.sleep(3000);

            WebElement element = driver.findElement(By.id("fare-finder-wdc"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            driver.findElement(By.className("fare-finder__sidebar__submit")).click(); //submit search


            Thread.sleep(25000);

            Select selectElementFirst = new Select(driver.findElements(By.tagName("select")).get(0));
            for (Month month : monthsUntilTheEndOfTheYear) {
                selectElementFirst.selectByVisibleText(month.getName().toLowerCase().substring(0, 1).toUpperCase() + month.getName().toLowerCase().substring(1) + " "  + GregorianCalendar.getInstance().get(Calendar.YEAR));
                List<WebElement> ul_elements = driver.findElements(By.xpath("//ul[@class='fare-finder__calendar__days__list']"));

                //departures
                List<WebElement> li_departures = ul_elements.get(0).findElements(By.tagName("li"));

                for (WebElement listElement : li_departures) {
                    try {
                        WebElement priceElement = listElement.findElement(By.className("fare-finder__calendar__days__day__label__price-price"));
                        WebElement dateElement = listElement.findElement(By.className("fare-finder__calendar__days__day__date"));
                        System.out.println("Price" + priceElement.getText() + "Date" + dateElement.getText());

                        Date date = new GregorianCalendar(2018, month.getNumber(), Integer.valueOf(dateElement.getText())).getTime();
                        airportsService.savePrice(ddRow.getDeparture().getId(), ddRow.getDestination().getId(), new BigDecimal(priceElement.getText().replaceAll(",", "")), date);

                    } catch (Exception e) {
                        continue;
                    }
                }


                Thread.sleep(5000);
            }

            //scroll into view

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElements(By.tagName("select")).get(1));
            Select selectElementReturn = new Select(driver.findElements(By.tagName("select")).get(1));

            Thread.sleep(500);
            for (Month month : monthsUntilTheEndOfTheYear) {

                selectElementReturn.selectByVisibleText(month.getName().toLowerCase().substring(0, 1).toUpperCase() + month.getName().toLowerCase().substring(1) + " 2018");


                List<WebElement> ul_elements = driver.findElements(By.xpath("//ul[@class='fare-finder__calendar__days__list']"));

                //departures
                List<WebElement> li_departures = ul_elements.get(1).findElements(By.tagName("li"));

                for (WebElement listElement : li_departures) {
                    try {
                        WebElement priceElement = listElement.findElement(By.className("fare-finder__calendar__days__day__label__price-price"));
                        WebElement dateElement = listElement.findElement(By.className("fare-finder__calendar__days__day__date"));
                        System.out.println("Price" + priceElement.getText() + "Date" + dateElement.getText());

                        Date date = new GregorianCalendar(2018, month.getNumber(), Integer.valueOf(dateElement.getText())).getTime();
                        airportsService.savePrice(ddRow.getDestination().getId(), ddRow.getDeparture().getId(), new BigDecimal(priceElement.getText().replaceAll(",", "")), date);

                    } catch (Exception e) {
                        continue;
                    }
                }


                Thread.sleep(5000);
            }
        }

        driver.quit();


        System.out.println("gotovo");


        //get data

        List<BestDealDto> bestDealDtoList = new ArrayList<>();
        for (WizzDeparturesDestinations departureAndDestination : departuresAndDestinations) {
            bestDealDtoList.addAll(airportsService.getTheBestDeals(departureAndDestination.getDeparture().getId(), departureAndDestination.getDestination().getId()));
        }
        //send email
        myMailService.sendEmail(bestDealDtoList);

    }


}
