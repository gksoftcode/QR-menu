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
 * A Country.
 */
@Document(collection = "country")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("country_name")
    private String countryName;

    @DBRef
    @Field("restorant")
    @JsonIgnoreProperties(value = { "restorantNames", "availableLangs", "account", "country" }, allowSetters = true)
    private Set<Restorant> restorants = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Country id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public Country countryName(String countryName) {
        this.setCountryName(countryName);
        return this;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Set<Restorant> getRestorants() {
        return this.restorants;
    }

    public void setRestorants(Set<Restorant> restorants) {
        if (this.restorants != null) {
            this.restorants.forEach(i -> i.setCountry(null));
        }
        if (restorants != null) {
            restorants.forEach(i -> i.setCountry(this));
        }
        this.restorants = restorants;
    }

    public Country restorants(Set<Restorant> restorants) {
        this.setRestorants(restorants);
        return this;
    }

    public Country addRestorant(Restorant restorant) {
        this.restorants.add(restorant);
        restorant.setCountry(this);
        return this;
    }

    public Country removeRestorant(Restorant restorant) {
        this.restorants.remove(restorant);
        restorant.setCountry(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        return id != null && id.equals(((Country) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Country{" +
            "id=" + getId() +
            ", countryName='" + getCountryName() + "'" +
            "}";
    }
}
