package com.persistence.dao;

import com.datamodel.WizzAirport;

import java.util.List;

public interface WizzAirportsDao {

     void save(WizzAirport wizzAirport);
     void edit(WizzAirport wizzAirport);
     void delete(int id);
     WizzAirport findById(int id);
     List<WizzAirport> getAll();



}
