package com.persistence.dao.impl;

import com.datamodel.WizzContacts;
import com.persistence.dao.ContactsDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactsDaoImpl implements ContactsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(WizzContacts wizzContacts) {
        sessionFactory.getCurrentSession().save(wizzContacts);
    }

    @Override
    public void edit(WizzContacts wizzContacts) {
        sessionFactory.getCurrentSession().update(wizzContacts);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public WizzContacts findById(int id) {
        return (WizzContacts)sessionFactory.getCurrentSession().get(WizzContacts.class, id);
    }

    @Override
    public List<WizzContacts> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from WizzContacts").getResultList();
    }
}
