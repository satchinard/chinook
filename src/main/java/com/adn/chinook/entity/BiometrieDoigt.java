package com.adn.chinook.entity;

import java.util.Objects;
import javax.persistence.*;

/**
 * Cette classe contient les informations de l'attribut et id de l'attribut des
 * informations biométriques.
 *
 * @author PIERRE
 * @see BaseEntite
 */
@Entity
@Table(name = "BIOMETRIES_DOIGT")
@SequenceGenerator(name = "BIOMETRIE_DOIGT_GEN", sequenceName = "BIOMETRIE_DOIGT_SEQUENCE_SEQ", allocationSize = 1)
public class BiometrieDoigt {

    /**
     * La version de l'objet
     */
    @Version
    private int version = 1;

    /**
     * Cette classe contient les informations liées à la biometrie
     *
     * @see Biometrie
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BIOMETRIE")
    protected Biometrie biometrie;
    /**
     * Cet attribut permet d'enregitrer l'empreinte digitale de la personne
     */
    @Column(name = "EMPREINTE_PG")//, columnDefinition = "bytea")
    protected byte[] empreintePG;
    @Column(name = "EMPREINTE_SQL")
    @Lob
    @Basic
    protected byte[] empreinteSQL;
    /**
     * Cet attribut permet d'enregitrer l'id de l'utilisateur dont l'empreinte
     * sera enregistré
     */
    @Column(name = "EMPREINTE_ID_USER_PG")//, columnDefinition = "bytea")
    protected byte[] empreinteIdUserPG;
    @Column(name = "EMPREINTE_ID_USER_SQL")
    @Lob
    @Basic
    protected byte[] empreinteIdUserSQL;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "BIOMETRIE_DOIGT_GEN")
    @Column(name = "ID", nullable = false)
    private Integer id;
    /**
     * Cet attribut permet d'enregitrer la main de l'emprunte
     */
    @Column(name = "MAIN")
    private String main;
    /**
     * Cet attribut permet d'enregitrer le doigt de l'emprunte
     */
    @Column(name = "DOIGT")
    private String doigt;
    /**
     * Cet attribut permet d'enregitrer le lien d'image de l'empreinte
     */
    @Column(name = "EMPREINTE_LIEN")
    private String empreinteLien;

    public BiometrieDoigt() {
    }

    public BiometrieDoigt(Biometrie biometrie) {
        this.biometrie = biometrie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Biometrie getBiometrie() {
        return biometrie;
    }

    public void setBiometrie(Biometrie biometrie) {
        this.biometrie = biometrie;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDoigt() {
        return doigt;
    }

    public void setDoigt(String doigt) {
        this.doigt = doigt;
    }

    public byte[] getEmpreinte() {
        return empreinteSQL;
    }

    public void setEmpreinte(byte[] empreinte) {
        this.empreinteSQL = empreinte;
    }

    public String getEmpreinteLien() {
        return empreinteLien;
    }

    public void setEmpreinteLien(String empreinteLien) {
        this.empreinteLien = empreinteLien;
    }

    public byte[] getEmpreinteIdUser() {
        return empreinteIdUserSQL;
    }

    public void setEmpreinteIdUser(byte[] empreinteIdUser) {
        this.empreinteIdUserSQL = empreinteIdUser;
    }

    public byte[] getEmpreintePG() {
        return empreintePG;
    }

    public void setEmpreintePG(byte[] empreintePG) {
        this.empreintePG = empreintePG;
    }

    public byte[] getEmpreinteSQL() {
        return empreinteSQL;
    }

    public void setEmpreinteSQL(byte[] empreinteSQL) {
        this.empreinteSQL = empreinteSQL;
    }

    public byte[] getEmpreinteIdUserPG() {
        return empreinteIdUserPG;
    }

    public void setEmpreinteIdUserPG(byte[] empreinteIdUserPG) {
        this.empreinteIdUserPG = empreinteIdUserPG;
    }

    public byte[] getEmpreinteIdUserSQL() {
        return empreinteIdUserSQL;
    }

    public void setEmpreinteIdUserSQL(byte[] empreinteIdUserSQL) {
        this.empreinteIdUserSQL = empreinteIdUserSQL;
    }

    @Override
    public String toString() {
        return "BiometrieDoigt{" + "id=" + id + ", main=" + main + ", doigt=" + doigt + ", empreinteLien=" + empreinteLien + '}';
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
        final BiometrieDoigt other = (BiometrieDoigt) obj;
        return !(!Objects.equals(this.id, other.id) && (this.id == null || !this.id.equals(other.id)));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BiometrieDoigt clone = null;
        try {
            clone = (BiometrieDoigt) super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        // on renvoie le clone
        return clone;
    }
}
