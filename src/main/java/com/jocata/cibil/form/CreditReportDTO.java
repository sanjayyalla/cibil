package com.jocata.cibil.form;

import java.time.LocalDate;
import java.util.List;

public class CreditReportDTO {
    private LocalDate generatedOn;
    private CustomerDTO customer;
    private CibilScoreDTO cibilScore;
    private List<AccountDTO> accounts;
    private List<EnquiryDTO> enquiries;
    private List<RemarkDTO> remarks;

    public LocalDate getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(LocalDate generatedOn) {
        this.generatedOn = generatedOn;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public CibilScoreDTO getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(CibilScoreDTO cibilScore) {
        this.cibilScore = cibilScore;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public List<EnquiryDTO> getEnquiries() {
        return enquiries;
    }

    public void setEnquiries(List<EnquiryDTO> enquiries) {
        this.enquiries = enquiries;
    }

    public List<RemarkDTO> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<RemarkDTO> remarks) {
        this.remarks = remarks;
    }
}
