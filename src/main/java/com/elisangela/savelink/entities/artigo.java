package com.elisangela.savelink.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="artigos")
public class artigo{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String link;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String autor;

    @Column
    private String foto;

    @Column
    private Boolean favorito;

    public artigo() {
    }

    public artigo(int id, String link, String titulo, String autor, String foto) {
        this.id = id;
        this.link = link;
        this.titulo = titulo;
        this.autor = autor;
        this.foto = foto;
    }

    public artigo(String link, String titulo, String autor, String foto) {
        this.link = link;
        this.titulo = titulo;
        this.autor = autor;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }
}
