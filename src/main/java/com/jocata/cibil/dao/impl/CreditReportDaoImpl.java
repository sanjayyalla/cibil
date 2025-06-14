package com.jocata.cibil.dao.impl;

import com.jocata.cibil.dao.CreditReportDao;
import com.jocata.cibil.entity.CreditReport;
import com.jocata.cibil.entity.Customer;
import com.jocata.cibil.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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

    @Override
    public CreditReport getCreditReportByPan(String panNumber) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        String hql = """
//            select distinct cr
//              from CreditReport cr
//              join fetch cr.customer c
//              left join fetch c.address
//              left join fetch cr.cibilScore
//              left join fetch cr.accounts
//             where c.panNumber = :pan
//            """;
//        Query<CreditReport> q = session.createQuery(hql, CreditReport.class)
//                .setParameter("pan", panNumber);
//        CreditReport result = q.uniqueResult();
//        if (result != null) {
//            Hibernate.initialize(result.getEnquiries());
//            Hibernate.initialize(result.getRemarks());
//        }
//        session.close();
//        return result;


        Session session = HibernateUtil.getSessionFactory().openSession();
        Customer customer = session.createQuery(
                        "from Customer where panNumber = :pan", Customer.class)
                .setParameter("pan", panNumber)
                .uniqueResult();

        if (customer == null) {
            session.close();
            return null;
        }

        CreditReport report = session.find(CreditReport.class, customer.getCreditReport().getReportId());
        if (report != null) {
            Hibernate.initialize(report.getAccounts());
            Hibernate.initialize(report.getEnquiries());
            Hibernate.initialize(report.getRemarks());
        }

        session.close();
        return report;
    }
}
