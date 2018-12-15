package com.service.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BestDealDto {


    private BigDecimal priceSum;
    private Date dateFrom;
    private Date dateTo;
    private String departure;
    private String destination;
    private String currency;

    public BestDealDto(BigDecimal priceSum, Date dateFrom, Date dateTo, String departure, String destination, String currency) {
        this.priceSum = priceSum;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.departure = departure;
        this.destination = destination;
        this.currency = currency;
    }

    public BigDecimal getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
