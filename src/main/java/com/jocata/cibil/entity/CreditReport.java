package com.jocata.cibil.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "creditreport")
public class CreditReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer reportId;

    @Column(name = "generated_on")
    private LocalDate generatedOn;

    @OneToOne(mappedBy = "creditReport", cascade = CascadeType.ALL)
    private Customer customer;

    @OneToOne(mappedBy = "creditReport", cascade = CascadeType.ALL)
    private CibilScore cibilScore;

    @OneToMany(mappedBy = "creditReport", cascade = CascadeType.ALL)
    private List<Account> accounts;

    @OneToMany(mappedBy = "creditReport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enquiry> enquiries;

    @OneToMany(mappedBy = "creditReport", cascade = CascadeType.ALL)
    private List<Remark> remarks;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public LocalDate getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(LocalDate generatedOn) {
        this.generatedOn = generatedOn;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CibilScore getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(CibilScore cibilScore) {
        this.cibilScore = cibilScore;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    public void setEnquiries(List<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }

    public List<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<Remark> remarks) {
        this.remarks = remarks;
    }
}
