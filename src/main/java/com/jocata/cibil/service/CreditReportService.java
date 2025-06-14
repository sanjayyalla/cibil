package com.jocata.cibil.service;

import com.jocata.cibil.form.CreditReportDTO;

public interface CreditReportService {
    CreditReportDTO createCreditReport(CreditReportDTO report);

    CreditReportDTO getCreditReportByPan(String panNumber);
}
