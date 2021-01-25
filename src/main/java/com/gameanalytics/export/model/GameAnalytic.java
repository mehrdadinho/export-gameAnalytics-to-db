package com.gameanalytics.export.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by m.peykari on 6/14/2020.
 */
@Entity
@Table(name = "Game_Analytic")
public class GameAnalytic {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name",length = 50)
    private String file_name;

    @Column(name = "link_url")
    private String linkUrl;

    @Column(name = "date_key")
    private Date dateKey;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "gameAnalytic")
    private User_Meta user_meta;

    @Column(name = "ip",length = 50)
    private String ip;

    @Column(name = "game_id",length = 50)
    private String game_id;

    @Column(name = "first_in_batch",length = 50)
    private String first_in_batch;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "gameAnalytic")
    private Data data;

    @Column(name = "country_code",length = 50)
    private String country_code;

    @Column(name = "arrival_ts",length = 50)
    private String arrival_ts;

    public GameAnalytic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Date getDateKey() {
        return dateKey;
    }

    public void setDateKey(Date dateKey) {
        this.dateKey = dateKey;
    }

    public User_Meta getUser_meta() {
        return user_meta;
    }

    public void setUser_meta(User_Meta user_meta) {
        this.user_meta = user_meta;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getFirst_in_batch() {
        return first_in_batch;
    }

    public void setFirst_in_batch(String first_in_batch) {
        this.first_in_batch = first_in_batch;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getArrival_ts() {
        return arrival_ts;
    }

    public void setArrival_ts(String arrival_ts) {
        this.arrival_ts = arrival_ts;
    }
}
