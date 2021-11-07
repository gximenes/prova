package com.giovani.letscode.provaletscode.application.repository;

import com.giovani.letscode.provaletscode.domain.entity.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LocalizacaoRebeldeRepository   extends JpaRepository<Localizacao, Long> {


    @Modifying
    @Query(" update Localizacao  l set l.nomeGalaxia = :nomeGalaxia, l.longitude = :longitude , l.latitude = :latitude " +
            " where l.idLocalizacao =  :idLocalizacao ")
    void atualizarLocallizacaoRebeldeByIdRebelde(@Param("idLocalizacao") Long idLocalizacao,
                                                 @Param("nomeGalaxia") String nomeGalaxia,
                                                 @Param("latitude") String latitude,
                                                 @Param("longitude") String longitude);


}
