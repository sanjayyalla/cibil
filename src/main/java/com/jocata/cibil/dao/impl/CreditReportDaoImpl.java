package com.jocata.cibil.dao.impl;

import com.jocata.cibil.dao.CreditReportDao;
import com.jocata.cibil.entity.CreditReport;
import com.jocata.cibil.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CreditReportDaoImpl implements CreditReportDao {
    @Override
    public CreditReport createCreditReport(CreditReport report) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(report);
        tx.commit();
        session.close();
        return report;
    }
}
