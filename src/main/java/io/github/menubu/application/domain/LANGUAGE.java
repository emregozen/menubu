package io.github.menubu.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A LANGUAGE.
 */
@Entity
@Table(name = "language")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "language")
public class LANGUAGE implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "c_ode")
    private String cODE;

    @Column(name = "n_ame")
    private String nAME;

    @Column(name = "c_reatedate")
    private Instant cREATEDATE;

    @Column(name = "c_reateuser")
    private String cREATEUSER;

    @Column(name = "u_pdatedate")
    private Instant uPDATEDATE;

    @Column(name = "u_pdateuser")
    private String uPDATEUSER;

    @Column(name = "u_sableinemail")
    private String uSABLEINEMAIL;

    @Column(name = "t_royacode")
    private String tROYACODE;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcODE() {
        return cODE;
    }

    public LANGUAGE cODE(String cODE) {
        this.cODE = cODE;
        return this;
    }

    public void setcODE(String cODE) {
        this.cODE = cODE;
    }

    public String getnAME() {
        return nAME;
    }

    public LANGUAGE nAME(String nAME) {
        this.nAME = nAME;
        return this;
    }

    public void setnAME(String nAME) {
        this.nAME = nAME;
    }

    public Instant getcREATEDATE() {
        return cREATEDATE;
    }

    public LANGUAGE cREATEDATE(Instant cREATEDATE) {
        this.cREATEDATE = cREATEDATE;
        return this;
    }

    public void setcREATEDATE(Instant cREATEDATE) {
        this.cREATEDATE = cREATEDATE;
    }

    public String getcREATEUSER() {
        return cREATEUSER;
    }

    public LANGUAGE cREATEUSER(String cREATEUSER) {
        this.cREATEUSER = cREATEUSER;
        return this;
    }

    public void setcREATEUSER(String cREATEUSER) {
        this.cREATEUSER = cREATEUSER;
    }

    public Instant getuPDATEDATE() {
        return uPDATEDATE;
    }

    public LANGUAGE uPDATEDATE(Instant uPDATEDATE) {
        this.uPDATEDATE = uPDATEDATE;
        return this;
    }

    public void setuPDATEDATE(Instant uPDATEDATE) {
        this.uPDATEDATE = uPDATEDATE;
    }

    public String getuPDATEUSER() {
        return uPDATEUSER;
    }

    public LANGUAGE uPDATEUSER(String uPDATEUSER) {
        this.uPDATEUSER = uPDATEUSER;
        return this;
    }

    public void setuPDATEUSER(String uPDATEUSER) {
        this.uPDATEUSER = uPDATEUSER;
    }

    public String getuSABLEINEMAIL() {
        return uSABLEINEMAIL;
    }

    public LANGUAGE uSABLEINEMAIL(String uSABLEINEMAIL) {
        this.uSABLEINEMAIL = uSABLEINEMAIL;
        return this;
    }

    public void setuSABLEINEMAIL(String uSABLEINEMAIL) {
        this.uSABLEINEMAIL = uSABLEINEMAIL;
    }

    public String gettROYACODE() {
        return tROYACODE;
    }

    public LANGUAGE tROYACODE(String tROYACODE) {
        this.tROYACODE = tROYACODE;
        return this;
    }

    public void settROYACODE(String tROYACODE) {
        this.tROYACODE = tROYACODE;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LANGUAGE lANGUAGE = (LANGUAGE) o;
        if (lANGUAGE.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lANGUAGE.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LANGUAGE{" +
            "id=" + getId() +
            ", cODE='" + getcODE() + "'" +
            ", nAME='" + getnAME() + "'" +
            ", cREATEDATE='" + getcREATEDATE() + "'" +
            ", cREATEUSER='" + getcREATEUSER() + "'" +
            ", uPDATEDATE='" + getuPDATEDATE() + "'" +
            ", uPDATEUSER='" + getuPDATEUSER() + "'" +
            ", uSABLEINEMAIL='" + getuSABLEINEMAIL() + "'" +
            ", tROYACODE='" + gettROYACODE() + "'" +
            "}";
    }
}
