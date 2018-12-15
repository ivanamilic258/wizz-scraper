package com.persistence.dao;

import com.datamodel.WizzContacts;

import java.util.List;

public interface ContactsDao {

     void save(WizzContacts wizzContacts);
     void edit(WizzContacts wizzContacts);
     void delete(int id);
     WizzContacts findById(int id);
     List<WizzContacts> getAll();



}
