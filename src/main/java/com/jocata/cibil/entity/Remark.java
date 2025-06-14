package com.jocata.cibil.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "remarks")
public class Remark {
    @Id
    @Column(name = "remark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer remarkId;

    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "report_id")
    private CreditReport creditReport;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    public Integer getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(Integer remarkId) {
        this.remarkId = remarkId;
    }

    public CreditReport getCreditReport() {
        return creditReport;
    }

    public void setCreditReport(CreditReport creditReport) {
        this.creditReport = creditReport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
