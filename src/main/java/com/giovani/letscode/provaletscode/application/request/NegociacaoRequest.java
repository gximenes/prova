package com.giovani.letscode.provaletscode.application.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@JsonSerialize
@Getter
@EqualsAndHashCode
public class NegociacaoRequest implements Serializable  {


    private Long idRebeldeSolicitante;

    private Long idRebeldeSolicitado;

    private List<RecursoRequest> recursosSolicitante;

    private List<RecursoRequest> recursosSolicitado;




}
