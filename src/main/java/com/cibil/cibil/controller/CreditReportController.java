package com.cibil.cibil.controller;

import com.cibil.cibil.form.CreditReportDTO;
import com.cibil.cibil.service.CreditReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CreditReportController {

    @Autowired
    private CreditReportService service;

    @PostMapping("/createCreditReport")
    public CreditReportDTO createCreditReport(@RequestBody CreditReportDTO report) {
        CreditReportDTO response = null;
        try {
            if (report.getCustomer().getPanNumber()!=null) {
                response = service.createCreditReport(report);
                if (response != null) {
                    return response;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @GetMapping("/getCreditReportByPan")
    public CreditReportDTO getCreditReportByPan(@RequestParam String panNumber){
        CreditReportDTO response = null;
        try{
            if(panNumber!=null && !panNumber.isEmpty()){
                response = service.getCreditReportByPan(panNumber);
                return response;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

