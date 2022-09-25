package com.elisangela.savelink.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elisangela.savelink.dtos.response;
import com.elisangela.savelink.entities.artigo;
import com.elisangela.savelink.service.artigoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/artigo")
public class artigoController {
    
    private artigoService artigoServicevice;

    public artigoController(artigoService artigoServicevice) {
        this.artigoServicevice = artigoServicevice;
    }

    @PostMapping(path = "/save_artigo")
    public response saveArtigo(@RequestBody artigo artigo){
        return artigoServicevice.saveArtigo(artigo);
    }

    @PutMapping(path = "/edit_artigo")
    public response editArtigo(@RequestBody artigo artigo){
        return artigoServicevice.editArtigo(artigo.getId(),artigo.getTitulo(),artigo.getAutor());
    }

    @GetMapping(path = "/lista_artigo")
    public response listaArtigo(){
        return artigoServicevice.getAllArtigo();
    }

    @DeleteMapping (path = "/delete_artigo/{id}")
    public response deleteArtigo(@PathVariable int id){
        return artigoServicevice.deleteArtigo(id);
    }

    @GetMapping(path = "/lista_favoritos")
    public response listaFavoritos(){
        return artigoServicevice.getListaFavoritos();
    }

    @PutMapping(path = "/delete_favoritos/{id}")
    public response deleteFavoritos(@PathVariable int id){
        return artigoServicevice.deleteFavoritos(id);
    }

    @PutMapping(path = "/save_favoritos/{id}")
    public response saveFavoritos(@PathVariable int id){
        return artigoServicevice.saveFavoritos(id);
    }

    @PostMapping(path = "/importar")
    public response importar(@RequestBody artigo artigo){
        return artigoServicevice.importarArtigos(artigo.getLink());
    }

    @GetMapping(path = "/get_artigo/{id}")
    public response getArtigo(@PathVariable int id){
        return artigoServicevice.getArtigoById(id);
    }

    public artigoService getArtigoServicevice() {
        return artigoServicevice;
    }

    public void setArtigoServicevice(artigoService artigoServicevice) {
        this.artigoServicevice = artigoServicevice;
    }
}
