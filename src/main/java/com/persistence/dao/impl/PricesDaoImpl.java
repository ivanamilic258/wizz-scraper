package com.persistence.dao.impl;

import com.datamodel.Price;
import com.persistence.dao.PricesDao;
import com.service.dto.BestDealDto;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PricesDaoImpl implements PricesDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Price price) {
        sessionFactory.getCurrentSession().save(price);
    }

    @Override
    public void edit(Price price) {
        sessionFactory.getCurrentSession().update(price);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public Price findById(int id) {
        return (Price) sessionFactory.getCurrentSession().get(Price.class, id);
    }

    @Override
    public List<Price> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Price").getResultList();
    }

    @Override
    public int hqlTruncate(){
        String hql = String.format("delete from Price");
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.executeUpdate();
    }

    @Override
    public Price getCheapestPrice(int departureId, int destinationId){
        String hql = "select p from Price p where p.departure.id = :departureId and p.destination.id = :destinationId order by price";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("departureId", departureId);
        query.setParameter("destinationId", destinationId);
        query.setMaxResults(1);
        return (Price) query.uniqueResult();

    }


    @Override
    public List<BestDealDto> getTheBestDeal(int departureId, int destinationId){
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT " +
                " NEW com.service.dto.BestDealDto(" +
                "p1.price + p2.price," +
                "p1.date," +
                "p2.date," +
                "p1.departure.name," +
                "p1.destination.name," +
                "a.currency) " +
                " FROM Price p1 " +
                "join Price p2  on p1.destination.id = p2.departure.id " +
                "join wizz_airports a on a.id = p1.departureId " +
                "where p1.departure.id=:departureId  and p1.destination.id =:destinationId " +
                "and datediff(p2.date, p1.date) between 4 and 8 " +
                "order by p1.price + p2.price");
        query.setParameter("departureId", departureId).setParameter("destinationId", destinationId).setMaxResults(5);
        return (List<BestDealDto>)query.list();
    }
}
