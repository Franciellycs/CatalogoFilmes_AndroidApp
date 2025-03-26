package com.example.newbanco;

public class Filme {
    public int id;
    public String nome;
    public String categoria;
    private String idioma;
    private String legenda;

    public Filme() {
    }

    public Filme(String nome, String categoria, String idioma, String legenda) {
        this.nome = nome;
        this.idioma = idioma;
        this.categoria = categoria;
        this.legenda = legenda;
    }

    @Override
    public String toString(){
        return nome + " | " + categoria;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }
}
