package com.persistence.dao.impl;

import com.datamodel.Month;
import com.datamodel.Price;
import com.persistence.dao.MonthsDao;
import com.persistence.dao.PricesDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MonthsDaoImpl implements MonthsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Month month) {
        sessionFactory.getCurrentSession().save(month);
    }

    @Override
    public void edit(Month month) {
        sessionFactory.getCurrentSession().update(month);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public Month findById(int id) {
        return (Month) sessionFactory.getCurrentSession().get(Month.class, id);
    }

    @Override
    public List<Month> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Month").getResultList();
    }
}
