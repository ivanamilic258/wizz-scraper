package com.service;


import com.datamodel.Month;
import com.datamodel.Price;
import com.datamodel.WizzAirport;
import com.datamodel.WizzDeparturesDestinations;
import com.service.dto.BestDealDto;
import com.service.dto.MonthAndYearDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AirportsService {


     List<WizzAirport> findAirports();


     void savePrice(int departureId, int destinationId, BigDecimal price, Date date);

     List<Month> getMonthsFromTheBeginingOfTheYear();

     List<Month> getMonthsUntilTheEndOfTheYear();

     List<WizzDeparturesDestinations> findDeparturesAndDestinations();


     void clearAllPrices();

     Price getCheapestFlight(int departureId, int destinationId);

     List<BestDealDto> getTheBestDeals(int departureId, int destinationId);

     List<MonthAndYearDto> getListOfMonthsUntilTheEndOfTheYear();

     List<MonthAndYearDto> getListOfMonthsUntilThisTimeNextYear();
}
