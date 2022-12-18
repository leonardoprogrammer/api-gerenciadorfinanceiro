package com.gerenciador.gfapi.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "GASTO")
public class Gasto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GASTO_ID_SEQUENCE")
    private long id;

    @NotNull
    @Column(name = "ID_USUARIO")
    private long idUsuario;

    @Size(max = 200)
    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "VALOR")
    private Double valor;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA")
    private Date data;

    @Size(max = 255)
    @Column(name = "DESCRICAO")
    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DTA_ADD")
    private Calendar dtaAdd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DTA_ALT")
    private Calendar dtaAlt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getDtaAdd() {
        return dtaAdd;
    }

    public void setDtaAdd(Calendar dtaAdd) {
        this.dtaAdd = dtaAdd;
    }

    public Calendar getDtaAlt() {
        return dtaAlt;
    }

    public void setDtaAlt(Calendar dtaAlt) {
        this.dtaAlt = dtaAlt;
    }
}
