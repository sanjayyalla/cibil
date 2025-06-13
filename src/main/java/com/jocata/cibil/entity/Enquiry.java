package com.jocata.cibil.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "enquiries")
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enquiry_id")
    private Integer enquiryId;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private CreditReport creditReport;

    @Column(name = "enquiry_date")
    private LocalDate enquiryDate;
    @Column(name = "member_name")
    private String memberName;
    @Column(name = "enquiry_purpose")
    private String enquiryPurpose;
    @Column(name = "enquiry_amount")
    private BigDecimal enquiryAmount;

    public Integer getEnquiryId() {
        return enquiryId;
    }

    public void setEnquiryId(Integer enquiryId) {
        this.enquiryId = enquiryId;
    }

    public CreditReport getCreditReport() {
        return creditReport;
    }

    public void setCreditReport(CreditReport creditReport) {
        this.creditReport = creditReport;
    }

    public LocalDate getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(LocalDate enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEnquiryPurpose() {
        return enquiryPurpose;
    }

    public void setEnquiryPurpose(String enquiryPurpose) {
        this.enquiryPurpose = enquiryPurpose;
    }

    public BigDecimal getEnquiryAmount() {
        return enquiryAmount;
    }

    public void setEnquiryAmount(BigDecimal enquiryAmount) {
        this.enquiryAmount = enquiryAmount;
    }
}
