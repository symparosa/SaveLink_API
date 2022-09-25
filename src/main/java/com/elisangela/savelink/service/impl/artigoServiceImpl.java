package com.elisangela.savelink.service.impl;

import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.elisangela.savelink.dtos.response;
import com.elisangela.savelink.entities.artigo;
import com.elisangela.savelink.enums.responseType;
import com.elisangela.savelink.repository.artigoRepository;
import com.elisangela.savelink.service.artigoService;

@Service
public class artigoServiceImpl implements artigoService {

    private artigoRepository artigoRepository;

    public artigoServiceImpl(artigoRepository artigoRepository) {
        this.artigoRepository = artigoRepository;
    }

    @Override
    public response editArtigo(int id, String titulo, String autor) {

        response response = new response();

        try {

            int artigoEdit = artigoRepository.updateArtigo(autor, titulo, id);

            if (artigoEdit == 1) {
                response.setResponseType(responseType.SUCESS);
                response.setObject(id);
                response.setMessage(" Artigo editado com sucesso.");
                return response;
            } else {
                response.setResponseType(responseType.ERROR);
                response.setObject(null);
                response.setMessage(" Falha ao editar o artigo.");
                return response;
            }

        } catch (Exception e) {
            response.setResponseType(responseType.ERROR);
            response.setObject(null);
            response.setMessage(" Falha ao executar a operação.");
            return response;
        }
    }

    @Override
    public response saveArtigo(artigo artigo) {

        response response = new response();
        artigo artigoSave = artigoRepository.save(artigo);

        if (artigoSave != null) {
            response.setResponseType(responseType.SUCESS);
            response.setObject(artigo);
            response.setMessage(" Artigo salvo com sucesso.");
            return response;
        } else {
            response.setResponseType(responseType.ERROR);
            response.setObject(null);
            response.setMessage(" Falha ao salvar o artigo.");
            return response;
        }
    }

    @Override
    public response getAllArtigo() {

        response response = new response();
        List<artigo> artigoGetAll = artigoRepository.findAll();

        if (artigoGetAll != null && !artigoGetAll.isEmpty()) {
            response.setResponseType(responseType.SUCESS);
            response.setObject(artigoGetAll);
            response.setMessage(" Listar artigos com sucesso.");
            return response;
        } else if (artigoGetAll == null) {
            response.setResponseType(responseType.ERROR);
            response.setObject(null);
            response.setMessage(" Falha ao listar artigos.");
            return response;
        } else {
            response.setResponseType(responseType.ERROR);
            response.setObject(null);
            response.setMessage(" A listar de artigos está vazia.");
            return response;
        }
    }

    @Override
    public response deleteArtigo(int id) {

        response response = new response();

        try {
            artigoRepository.deleteById(id);
            response.setResponseType(responseType.SUCESS);
            response.setObject(id);
            response.setMessage(" Artigo eliminado com sucesso.");
            return response;
        } catch (Exception e) {
            response.setResponseType(responseType.ERROR);
            response.setObject(e.getMessage());
            response.setMessage(" Artigo não existe.");
            return response;
        }
    }

    @Override
    public response getListaFavoritos() {

        response response = new response();
        List<artigo> artigoGetFavoritos = artigoRepository.findAllByFavorito();

        if (artigoGetFavoritos != null && !artigoGetFavoritos.isEmpty()) {
            response.setResponseType(responseType.SUCESS);
            response.setObject(artigoGetFavoritos);
            response.setMessage(" Listar favoritos com sucesso.");
            return response;
        } else if (artigoGetFavoritos == null) {
            response.setResponseType(responseType.ERROR);
            response.setObject(null);
            response.setMessage(" Falha ao listar favoritos.");
            return response;
        } else {
            response.setResponseType(responseType.ERROR);
            response.setObject(null);
            response.setMessage(" A listar de favoritos está vazia.");
            return response;
        }
    }

    @Override
    public response deleteFavoritos(int id) {

        response response = new response();

        try {

            int result = artigoRepository.updateFavoritos(0, id);

            if (result == 1) {
                response.setResponseType(responseType.SUCESS);
                response.setObject(id);
                response.setMessage(" Artigo removido de favoritos com sucesso.");
                return response;
            } else {
                response.setResponseType(responseType.ERROR);
                response.setObject(null);
                response.setMessage(" Falha ao remover artigo de favoritos.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseType(responseType.ERROR);
            response.setObject(e.getMessage());
            response.setMessage(" Falha ao executar a operação.");
            return response;
        }
    }

    @Override
    public response saveFavoritos(int id) {

        response response = new response();

        try {

            int val = validarFavoritos(id);

            if (val == 0) {

                int result = artigoRepository.updateFavoritos(1, id);

                if (result == 1) {
                    response.setResponseType(responseType.SUCESS);
                    response.setObject(id);
                    response.setMessage(" Artigo salvo no favoritos com sucesso.");
                    return response;
                } else {
                    response.setResponseType(responseType.ERROR);
                    response.setObject(null);
                    response.setMessage(" Falha ao salvar artigo no favoritos.");
                    return response;
                }
            } else {
                response.setResponseType(responseType.ERROR);
                response.setObject(null);
                response.setMessage(" Este artigo já se encontra na lista de favoritos. ");
                return response;
            }
        } catch (Exception e) {
            response.setResponseType(responseType.ERROR);
            response.setObject(e.getMessage());
            response.setMessage(" Falha ao executar a operação.");
            return response;
        }
    }

    public int validarFavoritos(int id) {

        try {

            String fav = artigoRepository.getFavoritos(id);
            
            if (fav.equals(null)) {
                return 0;
            } else if (fav.equals("true")){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public response importarArtigos(String linkBlog) {

        response response = new response();

        try {

            Connection con = Jsoup.connect(linkBlog);
            Document doc = con.get();

            if (con.response().statusCode() == 200) {

                int cont = 0, cont1 = 0;
                Elements divs = doc.select("div");

                String[] x = linkBlog.split("r/");
                String url = x[0] + "r";

                artigoRepository.truncateArtigos();

                for (Element div : divs) {

                    if (div.className().equals("blog-article-card css-g70b67")) {

                        String link = url + div.child(0).attr("href");
                        String titulo = div.child(0).select("img").first().attr("alt");
                        String[] img = div.child(0).select("img").attr("srcset").split("640w");
                        String foto = url + img[0];
                        String autor;

                        if (cont == 0) {
                            autor = div.child(3).select("a").first().text();
                        } else {
                            autor = div.child(2).select("a").first().text();
                        }

                        artigo artigo = new artigo(link, titulo, autor, foto);
                        response = saveArtigo(artigo);
                        cont++;
                    }
                    if (div.className().equals("blog-article-card css-ggoiry")) {

                        String link = url + div.child(1).attr("href");
                        String titulo = div.child(1).select("img").first().attr("alt");
                        List<Element> elements = div.child(7).select("img");
                        String autor = elements.get(1).attr("alt");
                        String[] img = div.child(1).select("img").attr("srcset").split("640w");
                        String foto = url + img[0];

                        artigo artigo = new artigo(link, titulo, autor, foto);
                        response = saveArtigo(artigo);

                    }
                    if (div.className().equals("blog-article-card css-iz5gmu")) {

                        String link = url + div.child(0).attr("href");
                        String titulo = div.child(0).select("img").first().attr("alt");
                        String[] img = div.child(0).select("img").attr("srcset").split("640w");
                        String foto = url + img[0];
                        String autor;

                        if (cont1 == 0) {
                            List<Element> elements = div.child(3).select("img");
                            autor = elements.get(1).attr("alt");
                        } else {
                            List<Element> elements = div.child(2).select("img");
                            autor = elements.get(1).attr("alt");
                        }

                        artigo artigo = new artigo(link, titulo, autor, foto);
                        response = saveArtigo(artigo);
                        cont1++;
                    }
                }
                return response;
            } else {
                response.setResponseType(responseType.ERROR);
                response.setObject(null);
                response.setMessage(" Falha ao importar artigos.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseType(responseType.ERROR);
            response.setObject(e.getMessage());
            response.setMessage(" Falha ao executar a operação.");
            return response;
        }
    }

    @Override
    public response getArtigoById(int id) {
        
        response response = new response();

        try {
            artigo artigo = artigoRepository.findById(id).get();

            response.setResponseType(responseType.SUCESS);
            response.setObject(artigo);
            response.setMessage(" Artigo encontrado com sucesso.");
            return response;

        } catch (Exception e) {
            response.setResponseType(responseType.ERROR);
            response.setObject(e.getMessage());
            response.setMessage(" Falha ao executar a operação.");
            return response;
        }
    }

    public artigoRepository getArtigoRepository() {
        return artigoRepository;
    }

    public void setArtigoRepository(artigoRepository artigoRepository) {
        this.artigoRepository = artigoRepository;
    }
}
