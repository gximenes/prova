package com.giovani.letscode.provaletscode.infrastructure.rowMapper;

import com.giovani.letscode.provaletscode.application.request.RebeldeRequest;
import com.giovani.letscode.provaletscode.domain.entity.Rebelde;
import com.giovani.letscode.provaletscode.domain.enuns.Genero;

public class RebeldeRowMapper  {


    public static Rebelde mapRow(RebeldeRequest rebeldeRequest)  {

       return  Rebelde.builder()
                .idade(rebeldeRequest.getIdade())
                .genero(Genero.getGeneroByDescription(rebeldeRequest.getGenero()))
                .nome(rebeldeRequest.getNome())
                .build();
    }
}
