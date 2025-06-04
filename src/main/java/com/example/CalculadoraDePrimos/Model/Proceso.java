package com.example.CalculadoraDePrimos.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigInteger;

@Entity
public class Proceso {
    @Id
    private int id;
    private int numeroCifras;
    private String estado;
    private BigInteger resultado;

    public Proceso(int id, int numeroCifras) {
        this.id = id;
        this.numeroCifras = numeroCifras;
        this.estado = "calculando";
        this.resultado = null;
    }

    public Proceso() {

    }

    public int getId() {
        return id;
    }

    public int getNumeroCifras() {
        return numeroCifras;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getResultado() {
        return resultado;
    }

    public void setResultado(BigInteger resultado) {
        this.resultado = resultado;
    }
}
