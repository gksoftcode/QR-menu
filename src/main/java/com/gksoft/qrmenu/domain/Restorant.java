package com.gksoft.qrmenu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Restorant.
 */
@Document(collection = "restorant")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Restorant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("lat")
    private String lat;

    @Field("lang")
    private String lang;

    @Field("is_active")
    private Boolean isActive;

    @Field("is_deleted")
    private Boolean isDeleted;

    @Field("logo_url")
    private String logoUrl;

    @Field("fb_url")
    private String fbUrl;

    @Field("insta_url")
    private String instaUrl;

    @Field("twitter_url")
    private String twitterUrl;

    @Field("youtube_url")
    private String youtubeUrl;

    @Field("google_url")
    private String googleUrl;

    @Field("restorantName")
    @JsonIgnoreProperties(value = { "restorant" }, allowSetters = true)
    private Set<RestorantName> restorantNames = new HashSet<>();

    @Field("availableLang")
    @JsonIgnoreProperties(value = { "restorant" }, allowSetters = true)
    private Set<AvailableLang> availableLangs = new HashSet<>();

    @DBRef
    @Field("country")
    private Country country;

    @DBRef
    @Field("account")
    @JsonIgnoreProperties(value = { "restorants" }, allowSetters = true)
    private ClientAccount account;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Restorant id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return this.lat;
    }

    public Restorant lat(String lat) {
        this.setLat(lat);
        return this;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return this.lang;
    }

    public Restorant lang(String lang) {
        this.setLang(lang);
        return this;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Restorant isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public Restorant isDeleted(Boolean isDeleted) {
        this.setIsDeleted(isDeleted);
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public Restorant logoUrl(String logoUrl) {
        this.setLogoUrl(logoUrl);
        return this;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getFbUrl() {
        return this.fbUrl;
    }

    public Restorant fbUrl(String fbUrl) {
        this.setFbUrl(fbUrl);
        return this;
    }

    public void setFbUrl(String fbUrl) {
        this.fbUrl = fbUrl;
    }

    public String getInstaUrl() {
        return this.instaUrl;
    }

    public Restorant instaUrl(String instaUrl) {
        this.setInstaUrl(instaUrl);
        return this;
    }

    public void setInstaUrl(String instaUrl) {
        this.instaUrl = instaUrl;
    }

    public String getTwitterUrl() {
        return this.twitterUrl;
    }

    public Restorant twitterUrl(String twitterUrl) {
        this.setTwitterUrl(twitterUrl);
        return this;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getYoutubeUrl() {
        return this.youtubeUrl;
    }

    public Restorant youtubeUrl(String youtubeUrl) {
        this.setYoutubeUrl(youtubeUrl);
        return this;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getGoogleUrl() {
        return this.googleUrl;
    }

    public Restorant googleUrl(String googleUrl) {
        this.setGoogleUrl(googleUrl);
        return this;
    }

    public void setGoogleUrl(String googleUrl) {
        this.googleUrl = googleUrl;
    }

    public Set<RestorantName> getRestorantNames() {
        return this.restorantNames;
    }

    public void setRestorantNames(Set<RestorantName> restorantNames) {
        if (this.restorantNames != null) {
            this.restorantNames.forEach(i -> i.setRestorant(null));
        }
        if (restorantNames != null) {
            restorantNames.forEach(i -> i.setRestorant(this));
        }
        this.restorantNames = restorantNames;
    }

    public Restorant restorantNames(Set<RestorantName> restorantNames) {
        this.setRestorantNames(restorantNames);
        return this;
    }

    public Restorant addRestorantName(RestorantName restorantName) {
        this.restorantNames.add(restorantName);
        return this;
    }

    public Restorant removeRestorantName(RestorantName restorantName) {
        this.restorantNames.remove(restorantName);
        return this;
    }

    public Set<AvailableLang> getAvailableLangs() {
        return this.availableLangs;
    }

    public void setAvailableLangs(Set<AvailableLang> availableLangs) {
        if (this.availableLangs != null) {
            this.availableLangs.forEach(i -> i.setRestorant(null));
        }
        if (availableLangs != null) {
            availableLangs.forEach(i -> i.setRestorant(this));
        }
        this.availableLangs = availableLangs;
    }

    public Restorant availableLangs(Set<AvailableLang> availableLangs) {
        this.setAvailableLangs(availableLangs);
        return this;
    }

    public Restorant addAvailableLang(AvailableLang availableLang) {
        this.availableLangs.add(availableLang);
        return this;
    }

    public Restorant removeAvailableLang(AvailableLang availableLang) {
        this.availableLangs.remove(availableLang);
        return this;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Restorant country(Country country) {
        this.setCountry(country);
        return this;
    }

    public ClientAccount getAccount() {
        return this.account;
    }

    public void setAccount(ClientAccount clientAccount) {
        this.account = clientAccount;
    }

    public Restorant account(ClientAccount clientAccount) {
        this.setAccount(clientAccount);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Restorant)) {
            return false;
        }
        return id != null && id.equals(((Restorant) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Restorant{" +
            "id=" + getId() +
            ", lat='" + getLat() + "'" +
            ", lang='" + getLang() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", isDeleted='" + getIsDeleted() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            ", fbUrl='" + getFbUrl() + "'" +
            ", instaUrl='" + getInstaUrl() + "'" +
            ", twitterUrl='" + getTwitterUrl() + "'" +
            ", youtubeUrl='" + getYoutubeUrl() + "'" +
            ", googleUrl='" + getGoogleUrl() + "'" +
            "}";
    }
}
