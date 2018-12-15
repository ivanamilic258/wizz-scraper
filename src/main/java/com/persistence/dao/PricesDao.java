package com.persistence.dao;

import com.datamodel.Price;
import com.service.dto.BestDealDto;

import java.util.List;

public interface PricesDao {

     void save(Price price);
     void edit(Price price);
     void delete(int id);
     Price findById(int id);
     List<Price> getAll();


     int hqlTruncate();

    Price getCheapestPrice(int departureId, int destinationId);

     List<BestDealDto> getTheBestDeal(int departureId, int destinationId);
}
