package com.gameanalytics.export.model;

import javax.persistence.*;

/**
 * Created by m.peykari on 6/14/2020.
 */
@Entity
@Table(name = "data")
public class Data {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_analytic_id")
    private GameAnalytic gameAnalytic;

    @Column(name = "value",length = 50)
    private String value;

    @Column(name = "v",length = 50)
    private String v;

    @Column(name = "user_id",length = 50)
    private String user_id;

    @Column(name = "session_num",length = 50)
    private String session_num;

    @Column(name = "session_id",length = 50)
    private String session_id;

    @Column(name = "sdk_version",length = 50)
    private String sdk_version;

    @Column(name = "platform",length = 50)
    private String platform;

    @Column(name = "os_version",length = 50)
    private String os_version;

    @Column(name = "manufacturer",length = 50)
    private String manufacturer;

    @Column(name = "google_aid_src",length = 50)
    private String google_aid_src;

    @Column(name = "google_aid",length = 50)
    private String google_aid;

    @Column(name = "event_id")
    private String event_id;

    @Column(name = "engine_version",length = 50)
    private String engine_version;

    @Column(name = "device",length = 50)
    private String device;

    @Column(name = "custom_01",length = 50)
    private String custom_01;

    @Column(name = "custom_02",length = 50)
    private String custom_02;

    @Column(name = "custom_03",length = 50)
    private String custom_03;

    @Column(name = "connection_type",length = 50)
    private String connection_type;

    @Column(name = "client_ts",length = 15)
    private String client_ts;

    @Column(name = "category",length = 50)
    private String category;

    @Column(name = "build",length = 50)
    private String build;

    @Column(name = "android_channel_id",length = 50)
    private String android_channel_id;

    @Column(name = "android_bundle_id",length = 50)
    private String android_bundle_id;

    @Column(name = "android_app_version",length = 50)
    private String android_app_version;

    @Column(name = "android_app_signature",length = 50)
    private String android_app_signature;

    @Column(name = "android_app_build",length = 50)
    private String android_app_build;

    @Column(name = "length",length = 50)
    private String length;

    @Column(name = "severity",length = 50)
    private String severity;

    @Column(name = "message")
    private String message;

    @Column(name = "amount",length = 50)
    private String amount;

    @Column(name = "limited_ad_tracking",length = 50)
    private String limited_ad_tracking;

    @Column(name = "attempt_num",length = 50)
    private String attempt_num;

    @Column(name = "reason")
    private String reason;

    @Column(name = "error_parameter",length = 50)
    private String error_parameter;

    @Column(name = "error_category",length = 50)
    private String error_category;

    @Column(name = "error_area",length = 50)
    private String error_area;

    @Column(name = "error_action",length = 50)
    private String error_action;

    @Column(name = "jailbroken",length = 50)
    private String jailbroken;

    @Column(name = "install",length = 50)
    private String install;

    @Column(name = "type",length = 50)
    private String type;

    @Column(name = "android_mac_sha1",length = 50)
    private String android_mac_sha1;

    @Column(name = "android_mac_md5",length = 50)
    private String android_mac_md5;

    @Column(name = "android_id",length = 50)
    private String android_id;

    @Column(name = "transaction_num",length = 50)
    private String transaction_num;

    @Column(name = "currency",length = 50)
    private String currency;

    @Column(name = "cart_type",length = 50)
    private String cart_type;

    @Column(name = "amount_usd",length = 50)
    private String amount_usd;


    public Data() {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSession_num() {
        return session_num;
    }

    public void setSession_num(String session_num) {
        this.session_num = session_num;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getSdk_version() {
        return sdk_version;
    }

    public void setSdk_version(String sdk_version) {
        this.sdk_version = sdk_version;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGoogle_aid_src() {
        return google_aid_src;
    }

    public void setGoogle_aid_src(String google_aid_src) {
        this.google_aid_src = google_aid_src;
    }

    public String getGoogle_aid() {
        return google_aid;
    }

    public void setGoogle_aid(String google_aid) {
        this.google_aid = google_aid;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEngine_version() {
        return engine_version;
    }

    public void setEngine_version(String engine_version) {
        this.engine_version = engine_version;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getCustom_01() {
        return custom_01;
    }

    public void setCustom_01(String custom_01) {
        this.custom_01 = custom_01;
    }

    public String getCustom_02() {
        return custom_02;
    }

    public void setCustom_02(String custom_02) {
        this.custom_02 = custom_02;
    }

    public String getCustom_03() {
        return custom_03;
    }

    public void setCustom_03(String custom_03) {
        this.custom_03 = custom_03;
    }

    public String getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(String connection_type) {
        this.connection_type = connection_type;
    }

    public String getClient_ts() {
        return client_ts;
    }

    public void setClient_ts(String client_ts) {
        this.client_ts = client_ts;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getAndroid_channel_id() {
        return android_channel_id;
    }

    public void setAndroid_channel_id(String android_channel_id) {
        this.android_channel_id = android_channel_id;
    }

    public String getAndroid_bundle_id() {
        return android_bundle_id;
    }

    public void setAndroid_bundle_id(String android_bundle_id) {
        this.android_bundle_id = android_bundle_id;
    }

    public String getAndroid_app_version() {
        return android_app_version;
    }

    public void setAndroid_app_version(String android_app_version) {
        this.android_app_version = android_app_version;
    }

    public String getAndroid_app_signature() {
        return android_app_signature;
    }

    public void setAndroid_app_signature(String android_app_signature) {
        this.android_app_signature = android_app_signature;
    }

    public String getAndroid_app_build() {
        return android_app_build;
    }

    public void setAndroid_app_build(String android_app_build) {
        this.android_app_build = android_app_build;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLimited_ad_tracking() {
        return limited_ad_tracking;
    }

    public void setLimited_ad_tracking(String limited_ad_tracking) {
        this.limited_ad_tracking = limited_ad_tracking;
    }

    public String getAttempt_num() {
        return attempt_num;
    }

    public void setAttempt_num(String attempt_num) {
        this.attempt_num = attempt_num;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getError_parameter() {
        return error_parameter;
    }

    public void setError_parameter(String error_parameter) {
        this.error_parameter = error_parameter;
    }

    public String getError_category() {
        return error_category;
    }

    public void setError_category(String error_category) {
        this.error_category = error_category;
    }

    public String getError_area() {
        return error_area;
    }

    public void setError_area(String error_area) {
        this.error_area = error_area;
    }

    public String getError_action() {
        return error_action;
    }

    public void setError_action(String error_action) {
        this.error_action = error_action;
    }

    public String getJailbroken() {
        return jailbroken;
    }

    public void setJailbroken(String jailbroken) {
        this.jailbroken = jailbroken;
    }

    public String getInstall() {
        return install;
    }

    public void setInstall(String install) {
        this.install = install;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAndroid_mac_sha1() {
        return android_mac_sha1;
    }

    public void setAndroid_mac_sha1(String android_mac_sha1) {
        this.android_mac_sha1 = android_mac_sha1;
    }

    public String getAndroid_mac_md5() {
        return android_mac_md5;
    }

    public void setAndroid_mac_md5(String android_mac_md5) {
        this.android_mac_md5 = android_mac_md5;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getTransaction_num() {
        return transaction_num;
    }

    public void setTransaction_num(String transaction_num) {
        this.transaction_num = transaction_num;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCart_type() {
        return cart_type;
    }

    public void setCart_type(String cart_type) {
        this.cart_type = cart_type;
    }

    public String getAmount_usd() {
        return amount_usd;
    }

    public void setAmount_usd(String amount_usd) {
        this.amount_usd = amount_usd;
    }
}
