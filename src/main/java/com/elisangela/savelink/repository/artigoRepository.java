package com.elisangela.savelink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.elisangela.savelink.entities.artigo;

@Transactional
public interface artigoRepository extends JpaRepository<artigo, Integer>{
    
    @Query(value = "select * from artigos where favorito = 1", nativeQuery = true)
    List<artigo> findAllByFavorito();

    @Modifying
    @Query(value = "update artigos set favorito =:fav where id=:id", nativeQuery = true)
    int updateFavoritos(@Param("fav") int fav,@Param("id") int id);

    @Query(value = "select favorito from artigos where id=:id", nativeQuery = true)
    String getFavoritos(@Param("id") int id);

    @Modifying
    @Query(value = "update artigos set autor =:autor, titulo =:titulo where id=:id", nativeQuery = true)
    int updateArtigo(@Param("autor") String autor,@Param("titulo") String titulo,@Param("id") int id);

    @Modifying
    @Query(value = "truncate table artigos", nativeQuery = true)
    void truncateArtigos();
}
