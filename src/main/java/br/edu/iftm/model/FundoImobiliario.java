package br.edu.iftm.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FundoImobiliario {
    private String nome;
    private String setor;
    private double preco;
    private Date dataIpo;

    public FundoImobiliario(String nome, String setor, double preco, Date dataIpo) {
        this.nome = nome;
        this.setor = setor;
        this.preco = preco;
        this.dataIpo = dataIpo;
    }

    public String getNome() { return nome; }
    public String getSetor() { return setor; }
    public double getPreco() { return preco; }
    public String getDataFormatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataIpo);
    }
}
