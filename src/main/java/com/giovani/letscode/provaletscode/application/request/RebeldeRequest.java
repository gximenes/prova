package com.giovani.letscode.provaletscode.application.request;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@JsonSerialize
@Getter
@EqualsAndHashCode
public class RebeldeRequest implements Serializable {

    private Long idRebelde;

    @NotNull(message = "Parâmetro Nome não pode ser nulo")
    @NotEmpty(message = "Parâmetro Nome não pode ser vazio")
    private String nome;

    @NotNull(message = "Parâmetro Idade não pode ser nulo")
    private Integer idade;

    @NotNull(message = "Parâmetro Genero não pode ser nulo")
    @NotEmpty(message = "Parâmetro Genero não pode ser vazio")
    private String genero;

    @NotNull(message = "Parâmetro Latitude não pode ser nulo")
    @NotEmpty(message = "Parâmetro Latitude não pode ser vazio")
    private String latitude;

    @NotNull(message = "Parâmetro Longitude não pode ser nulo")
    @NotEmpty(message = "Parâmetro Longitude não pode ser vazio")
    private String longitude;

    @NotNull(message = "Parâmetro Nome Galáxia não pode ser nulo")
    @NotEmpty(message = "Parâmetro Nome Galáxia não pode ser vazio")
    private String nomeGalaxia;

    private Map<String,Integer> inventarios;

}
