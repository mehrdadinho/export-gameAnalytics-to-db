package com.gameanalytics.export.model;

import javax.persistence.*;

/**
 * Created by m.peykari on 6/14/2020.
 */
@Entity
@Table(name = "revenue")
public class Revenue {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_meta_id")
    private User_Meta user_meta;

    @Column(name = "usd",length = 15)
    private String usd;

    public Revenue() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User_Meta getUser_meta() {
        return user_meta;
    }

    public void setUser_meta(User_Meta user_meta) {
        this.user_meta = user_meta;
    }

    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }
}
