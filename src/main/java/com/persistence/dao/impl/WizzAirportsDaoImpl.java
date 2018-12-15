package com.persistence.dao.impl;

import com.datamodel.WizzAirport;
import com.persistence.dao.WizzAirportsDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WizzAirportsDaoImpl implements WizzAirportsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(WizzAirport wizzAirport) {
        sessionFactory.getCurrentSession().save(wizzAirport);
    }

    @Override
    public void edit(WizzAirport wizzAirport) {
        sessionFactory.getCurrentSession().update(wizzAirport);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public WizzAirport findById(int id) {
        return (WizzAirport)sessionFactory.getCurrentSession().get(WizzAirport.class, id);
    }

    @Override
    public List<WizzAirport> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from WizzAirport").getResultList();
    }
}
