package com.cibil.cibil.service;

import com.cibil.cibil.form.CreditReportDTO;

public interface CreditReportService {
    CreditReportDTO createCreditReport(CreditReportDTO report);

    CreditReportDTO getCreditReportByPan(String panNumber);
}
