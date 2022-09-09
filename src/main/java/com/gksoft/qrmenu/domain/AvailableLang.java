package com.gksoft.qrmenu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A AvailableLang.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AvailableLang implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("code")
    private String code;

    @DBRef
    @Field("restorant")
    @JsonIgnoreProperties(value = { "restorantNames", "availableLangs", "account" }, allowSetters = true)
    private Restorant restorant;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getCode() {
        return this.code;
    }

    public AvailableLang code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Restorant getRestorant() {
        return this.restorant;
    }

    public void setRestorant(Restorant restorant) {
        this.restorant = restorant;
    }

    public AvailableLang restorant(Restorant restorant) {
        this.setRestorant(restorant);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvailableLang)) {
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
        return "AvailableLang{" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
