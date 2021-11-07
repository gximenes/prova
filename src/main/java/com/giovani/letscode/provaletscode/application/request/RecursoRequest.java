package com.giovani.letscode.provaletscode.application.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;


@JsonSerialize
@Getter
@EqualsAndHashCode
public class RecursoRequest implements Serializable {

    private String nome;

    private Integer quantidade;



}
