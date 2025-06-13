package com.jocata.cibil.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cibilscore")
public class CibilScore {

    @Id
    @Column(name = "score_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scoreId;

    @OneToOne
    @JoinColumn(name = "report_id", referencedColumnName = "report_id")
    private CreditReport creditReport;

    @Column(name = "score")
    private Integer score;

    @Column(name = "score_date")
    private LocalDate scoreDate;

    @Column(name = "risk_grade")
    private String riskGrade;

    public LocalDate getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(LocalDate scoreDate) {
        this.scoreDate = scoreDate;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public CreditReport getCreditReport() {
        return creditReport;
    }

    public void setCreditReport(CreditReport creditReport) {
        this.creditReport = creditReport;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRiskGrade() {
        return riskGrade;
    }

    public void setRiskGrade(String riskGrade) {
        this.riskGrade = riskGrade;
    }
}
