package com.service.impl;

import com.datamodel.Month;
import com.datamodel.Price;
import com.datamodel.WizzAirport;
import com.datamodel.WizzDeparturesDestinations;
import com.persistence.dao.MonthsDao;
import com.persistence.dao.PricesDao;
import com.persistence.dao.WizzAirportsDao;
import com.persistence.dao.WizzDeparturesDestinationsDao;
import com.service.AirportsService;
import com.service.dto.BestDealDto;
import com.service.dto.MonthAndYearDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class AirportsServiceImpl implements AirportsService {

    @Autowired
    private WizzAirportsDao wizzAirportsDao;
    @Autowired
    private PricesDao pricesDao;
    @Autowired
    private MonthsDao monthsDao;
    @Autowired
    private WizzDeparturesDestinationsDao wizzDeparturesDestinationsDao;

    @Override
    public List<WizzAirport> findAirports() {
       return wizzAirportsDao.getAll();
    }

    @Override
    public void savePrice(int departureId, int destinationId, BigDecimal price, Date date) {
        Price entity = new Price();
        entity.setDeparture(wizzAirportsDao.findById(departureId));
        entity.setDestination(wizzAirportsDao.findById(destinationId));
        entity.setPrice(price);
        entity.setDate(date);
        pricesDao.save(entity);
    }





    @Override
    public List<Month> getMonthsFromTheBeginingOfTheYear() {
        List<Month> months = monthsDao.getAll();
        months.removeIf(e -> e.getNumber() > Calendar.getInstance().get(Calendar.MONTH));
        return months;
    }


    @Override
    public List<Month> getMonthsUntilTheEndOfTheYear() {
        List<Month> months = monthsDao.getAll();
        months.removeIf(e -> e.getNumber() < Calendar.getInstance().get(Calendar.MONTH));
        return months;
    }

    @Override
    public List<WizzDeparturesDestinations> findDeparturesAndDestinations() {
        return wizzDeparturesDestinationsDao.getAll();
    }

    @Override
    public void clearAllPrices(){
         pricesDao.hqlTruncate();
    }


    @Override
    public Price getCheapestFlight(int departureId, int destinationId){
        return pricesDao.getCheapestPrice(departureId, destinationId);
    }

    @Override
    public List<BestDealDto> getTheBestDeals(int departureId, int destinationId){
        return  pricesDao.getTheBestDeal(departureId,destinationId);
    }

    @Override
    public List<MonthAndYearDto> getListOfMonthsUntilTheEndOfTheYear() {
        List<MonthAndYearDto> list = new ArrayList<>();
        List<Month> monthInThisYear = getMonthsUntilTheEndOfTheYear();
        for (Month month : monthInThisYear) {
            list.add(new MonthAndYearDto(month.getName(), String.valueOf(Calendar.getInstance().get(Calendar.YEAR)), month.getNumber()));
        }
        return list;
    }
    @Override
    public List<MonthAndYearDto> getListOfMonthsUntilThisTimeNextYear() {
        List<MonthAndYearDto> list = new ArrayList<>();
        List<Month> monthsInThisYear = getMonthsUntilTheEndOfTheYear();
        for (Month month : monthsInThisYear) {
            list.add(new MonthAndYearDto(month.getName(), String.valueOf(Calendar.getInstance().get(Calendar.YEAR)), month.getNumber()));
        }
        List<Month> monthsInNextYear = getMonthsFromTheBeginingOfTheYear();
        for (Month month : monthsInNextYear) {
            list.add(new MonthAndYearDto(month.getName(),String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 1), month.getNumber()));
        }
        return list;
    }

}
