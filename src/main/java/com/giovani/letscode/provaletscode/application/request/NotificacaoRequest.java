package com.giovani.letscode.provaletscode.application.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@JsonSerialize
@Getter
@EqualsAndHashCode
public class NotificacaoRequest implements Serializable {


    @NotNull(message = "Parâmetro idRebeldeDenunciante não pode ser nulo")
    private Long idRebeldeDenunciante;

    @NotNull(message = "Parâmetro idRebeldeDenunciado não pode ser nulo")
    private Long idRebeldeDenunciado;


    @NotNull(message = "Parâmetro motivo não pode ser nulo")
    @NotEmpty(message = "Parâmetro motivo não pode ser vazio")
    private String motivo;



}
