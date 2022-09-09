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
 * A ClientAccount.
 */
@Document(collection = "client_account")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("full_name")
    private String fullName;

    @Field("main_address")
    private String mainAddress;

    @Field("logo_url")
    private String logoUrl;

    @Field("is_deleted")
    private Boolean isDeleted;

    @DBRef
    @Field("restorant")
    @JsonIgnoreProperties(value = { "restorantNames", "availableLangs", "account", "country" }, allowSetters = true)
    private Set<Restorant> restorants = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ClientAccount id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public ClientAccount fullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMainAddress() {
        return this.mainAddress;
    }

    public ClientAccount mainAddress(String mainAddress) {
        this.setMainAddress(mainAddress);
        return this;
    }

    public void setMainAddress(String mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public ClientAccount logoUrl(String logoUrl) {
        this.setLogoUrl(logoUrl);
        return this;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public ClientAccount isDeleted(Boolean isDeleted) {
        this.setIsDeleted(isDeleted);
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<Restorant> getRestorants() {
        return this.restorants;
    }

    public void setRestorants(Set<Restorant> restorants) {
        if (this.restorants != null) {
            this.restorants.forEach(i -> i.setAccount(null));
        }
        if (restorants != null) {
            restorants.forEach(i -> i.setAccount(this));
        }
        this.restorants = restorants;
    }

    public ClientAccount restorants(Set<Restorant> restorants) {
        this.setRestorants(restorants);
        return this;
    }

    public ClientAccount addRestorant(Restorant restorant) {
        this.restorants.add(restorant);
        restorant.setAccount(this);
        return this;
    }

    public ClientAccount removeRestorant(Restorant restorant) {
        this.restorants.remove(restorant);
        restorant.setAccount(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientAccount)) {
            return false;
        }
        return id != null && id.equals(((ClientAccount) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientAccount{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", mainAddress='" + getMainAddress() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            ", isDeleted='" + getIsDeleted() + "'" +
            "}";
    }
}
