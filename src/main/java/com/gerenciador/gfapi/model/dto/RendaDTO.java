package com.gerenciador.gfapi.model.dto;

import java.sql.Timestamp;
import java.util.Date;

public class RendaDTO {

    private long idUsuario;
    private String titulo;
    private Double valor;
    private Date data;
    private String descricao;
    private Timestamp dtaAdd;
    private Timestamp dtaAlt;

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

    public Timestamp getDtaAdd() {
        return dtaAdd;
    }

    public void setDtaAdd(Timestamp dtaAdd) {
        this.dtaAdd = dtaAdd;
    }

    public Timestamp getDtaAlt() {
        return dtaAlt;
    }

    public void setDtaAlt(Timestamp dtaAlt) {
        this.dtaAlt = dtaAlt;
    }
}
