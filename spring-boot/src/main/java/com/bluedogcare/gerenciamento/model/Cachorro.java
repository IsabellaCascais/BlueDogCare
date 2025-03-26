package com.bluedogcare.gerenciamento.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cachorro")
public class Cachorro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nome;
    private String sexo;
    private String raca;
    private String porte;
    private String castrado;
    private String dataNasc;
    
    @Column(length = 100)
    private String obs;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public Cachorro() {
    }

    public Cachorro(int id, String nome, String sexo, String raca, String porte, String castrado, String dataNasc, String obs, Tutor tutor) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.porte = porte;
        this.castrado = castrado;
        this.dataNasc = dataNasc;
        this.obs = obs;
        this.tutor = tutor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getCastrado() {
        return castrado;
    }

    public void setCastrado(String castrado) {
        this.castrado = castrado;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
    
    
}
