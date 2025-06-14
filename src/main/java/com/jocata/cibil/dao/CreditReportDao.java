package com.jocata.cibil.dao;

import com.jocata.cibil.entity.CreditReport;

public interface CreditReportDao {
    CreditReport createCreditReport(CreditReport report);

    CreditReport getCreditReportByPan(String panNumber);
}
