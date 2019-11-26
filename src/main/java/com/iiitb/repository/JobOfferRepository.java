package com.iiitb.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.iiitb.beans.JobOffer;
import com.iiitb.utils.DBUtils;

public class JobOfferRepository {
    public List<JobOffer> findAll(){
        Session session = DBUtils.getSession();
        Transaction transaction = session.beginTransaction();
        
        String hql = "FROM JobOffer";
        Query query = session.createQuery(hql);
        List<JobOffer> offers = query.list();
        transaction.commit();
        session.close();
        return offers;
    }
}
