package com.persistence.dao;

import com.datamodel.Price;
import com.datamodel.WizzDeparturesDestinations;

import java.util.List;

public interface WizzDeparturesDestinationsDao {

     void save(WizzDeparturesDestinations wizzDeparturesDestinations);
     void edit(WizzDeparturesDestinations wizzDeparturesDestinations);
     void delete(int id);
     WizzDeparturesDestinations findById(int id);
     List<WizzDeparturesDestinations> getAll();



}
