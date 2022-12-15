package com.gerenciador.gfapi.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_ID_SEQUENCE")
    private long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Size(max = 150)
    @Column(name = "NOME")
    private String nome;

    @Size(max = 20)
    @Column(name = "CPF")
    private String cpf;

    @Size(max = 60)
    @Column(name = "EMAIL")
    private String email;

    @Size(max = 20)
    @Column(name = "TELEFONE")
    private String telefone;

    @Temporal(TemporalType.DATE)
    @Column(name = "NASCIMENTO")
    private Date nascimento;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTA_ADD")
    private Date dtaAdd;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTA_ALT")
    private Date dtaAlt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getDtaAdd() {
        return dtaAdd;
    }

    public void setDtaAdd(Date dtaAdd) {
        this.dtaAdd = dtaAdd;
    }

    public Date getDtaAlt() {
        return dtaAlt;
    }

    public void setDtaAlt(Date dtaAlt) {
        this.dtaAlt = dtaAlt;
    }
}
