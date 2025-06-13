package com.jocata.cibil.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EnquiryDTO {
    private LocalDate enquiryDate;
    private String memberName;
    private String enquiryPurpose;
    private BigDecimal enquiryAmount;

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
