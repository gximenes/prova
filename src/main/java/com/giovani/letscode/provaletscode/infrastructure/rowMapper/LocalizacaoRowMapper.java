package com.giovani.letscode.provaletscode.infrastructure.rowMapper;

import com.giovani.letscode.provaletscode.application.request.RebeldeRequest;
import com.giovani.letscode.provaletscode.domain.entity.Localizacao;
import com.giovani.letscode.provaletscode.domain.entity.Rebelde;
import com.giovani.letscode.provaletscode.domain.enuns.Genero;

import java.util.List;

public class LocalizacaoRowMapper {


    public static Localizacao mapRow(RebeldeRequest rebeldeRequest, List<Rebelde> rebeldes)  {

        return  Localizacao.builder()
                .latitude(rebeldeRequest.getLatitude())
                .longitude(rebeldeRequest.getLongitude())
                .nomeGalaxia(rebeldeRequest.getNomeGalaxia())
                .rebeldes(rebeldes)
                .build();


    }
}
