package com.gameanalytics.export.model;

import javax.persistence.*;

/**
 * Created by m.peykari on 6/14/2020.
 */
@Entity
@Table(name = "user_meta")
public class User_Meta {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_analytic_id")
    private GameAnalytic gameAnalytic;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user_meta")
    private Revenue revenue;

    @Column(name = "origin",length = 15)
    private String origin;

    @Column(name = "is_converting",length = 15)
    private String is_converting;

    @Column(name = "install_ts",length = 15)
    private String install_ts;

    @Column(name = "install_hour",length = 15)
    private String install_hour;

    @Column(name = "first_build",length = 15)
    private String first_build;

    @Column(name = "cohort_week",length = 15)
    private String cohort_week;

    @Column(name = "cohort_month",length = 15)
    private String cohort_month;

    @Column(name = "pay_ft",length = 15)
    private String pay_ft;

    @Column(name = "is_paying",length = 15)
    private String is_paying;

    @Column(name = "receipt_status",length = 50)
    private String receipt_status;

    public User_Meta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameAnalytic getGameAnalytic() {
        return gameAnalytic;
    }

    public void setGameAnalytic(GameAnalytic gameAnalytic) {
        this.gameAnalytic = gameAnalytic;
    }

    public Revenue getRevenue() {
        return revenue;
    }

    public void setRevenue(Revenue revenue) {
        this.revenue = revenue;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIs_converting() {
        return is_converting;
    }

    public void setIs_converting(String is_converting) {
        this.is_converting = is_converting;
    }

    public String getInstall_ts() {
        return install_ts;
    }

    public void setInstall_ts(String install_ts) {
        this.install_ts = install_ts;
    }

    public String getInstall_hour() {
        return install_hour;
    }

    public void setInstall_hour(String install_hour) {
        this.install_hour = install_hour;
    }

    public String getFirst_build() {
        return first_build;
    }

    public void setFirst_build(String first_build) {
        this.first_build = first_build;
    }

    public String getCohort_week() {
        return cohort_week;
    }

    public void setCohort_week(String cohort_week) {
        this.cohort_week = cohort_week;
    }

    public String getCohort_month() {
        return cohort_month;
    }

    public void setCohort_month(String cohort_month) {
        this.cohort_month = cohort_month;
    }

    public String getPay_ft() {
        return pay_ft;
    }

    public void setPay_ft(String pay_ft) {
        this.pay_ft = pay_ft;
    }

    public String getIs_paying() {
        return is_paying;
    }

    public void setIs_paying(String is_paying) {
        this.is_paying = is_paying;
    }

    public String getReceipt_status() {
        return receipt_status;
    }

    public void setReceipt_status(String receipt_status) {
        this.receipt_status = receipt_status;
    }
}
