package com.adn.chinook.entity;

import javax.persistence.*;

/**
 * Cette classe contient les informations de l'attribut et id de l'attribut des
 * informations biométriques.
 *
 * @author PIERRE
 * @see BaseEntite
 */
@Entity
@Table(name = "BIOMETRIES")
@SequenceGenerator(name = "BIOMETRIE_GEN", sequenceName = "BIOMETRIE_SEQUENCE_SEQ", allocationSize = 1)
public class Biometrie {

    /**
     * La version de l'objet
     */
    @Version
    private int version = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "BIOMETRIE_GEN")
    @Column(name = "ID", nullable = false)
    private Integer id;
    /**
     * Nom de l'attribut
     *
     */
    @Column(name = "TYPE")
    private String type;
    /**
     * Id de l'attribut
     *
     */
    @Column(name = "ID_TYPE")
    private String idType;

    public Biometrie() {
    }

    public Biometrie(String type, String idType) {
        this.type = type;
        this.idType = idType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Override
    public String toString() {
        return "Biometrie{" + "id=" + id + ", type=" + type + ", idType=" + idType + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Biometrie other = (Biometrie) obj;
        return !(this.id != other.id && (this.id == null || !this.id.equals(other.id)));
    }

    @Override
    public Object clone() {
        Biometrie clone = null;
        try {
            clone = (Biometrie) super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        // on renvoie le clone
        return clone;
    }
}
