package com.elisangela.savelink.service;

import com.elisangela.savelink.dtos.response;
import com.elisangela.savelink.entities.artigo;

public interface artigoService {
    
    response saveArtigo(artigo artigo);

    response editArtigo(int id, String titulo, String autor);

    response deleteArtigo(int id);

    response getAllArtigo();

    response getListaFavoritos();

    response deleteFavoritos(int id);

    response saveFavoritos(int id);

    response  importarArtigos(String linkBlog); 

    response getArtigoById(int id);
}
