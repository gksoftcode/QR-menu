package com.gksoft.qrmenu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A RestorantName.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestorantName implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("lang_code")
    private String langCode;

    @Field("name")
    private String name;

    @DBRef
    @Field("restorant")
    @JsonIgnoreProperties(value = { "restorantNames", "availableLangs", "account", "country" }, allowSetters = true)
    private Restorant restorant;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getLangCode() {
        return this.langCode;
    }

    public RestorantName langCode(String langCode) {
        this.setLangCode(langCode);
        return this;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getName() {
        return this.name;
    }

    public RestorantName name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restorant getRestorant() {
        return this.restorant;
    }

    public void setRestorant(Restorant restorant) {
        this.restorant = restorant;
    }

    public RestorantName restorant(Restorant restorant) {
        this.setRestorant(restorant);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestorantName)) {
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestorantName{" +
            ", langCode='" + getLangCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
