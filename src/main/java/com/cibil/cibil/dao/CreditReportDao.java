package com.cibil.cibil.dao;

import com.cibil.cibil.entity.CreditReport;

public interface CreditReportDao {
    CreditReport createCreditReport(CreditReport report);

    CreditReport getCreditReportByPan(String panNumber);

    CreditReport updateCreditReport(CreditReport creditReport);
}
