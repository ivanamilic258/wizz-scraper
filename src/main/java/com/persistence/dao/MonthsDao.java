package com.persistence.dao;

import com.datamodel.Month;
import com.datamodel.Price;

import java.util.List;

public interface MonthsDao {

     void save(Month month);
     void edit(Month month);
     void delete(int id);
     Month findById(int id);
     List<Month> getAll();



}
