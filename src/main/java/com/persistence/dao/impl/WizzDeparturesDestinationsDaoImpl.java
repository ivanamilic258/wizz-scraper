package com.persistence.dao.impl;

import com.datamodel.Price;
import com.datamodel.WizzDeparturesDestinations;
import com.persistence.dao.WizzDeparturesDestinationsDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WizzDeparturesDestinationsDaoImpl implements WizzDeparturesDestinationsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(WizzDeparturesDestinations wizzDeparturesDestinations) {
        sessionFactory.getCurrentSession().save(wizzDeparturesDestinations);
    }

    @Override
    public void edit(WizzDeparturesDestinations wizzDeparturesDestinations) {
        sessionFactory.getCurrentSession().update(wizzDeparturesDestinations);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public WizzDeparturesDestinations findById(int id) {
        return (WizzDeparturesDestinations) sessionFactory.getCurrentSession().get(WizzDeparturesDestinations.class, id);
    }

    @Override
    public List<WizzDeparturesDestinations> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from WizzDeparturesDestinations").getResultList();
    }
}
