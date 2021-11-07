package com.giovani.letscode.provaletscode.application.Response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Response<T> implements Serializable {

    private String mensagemRetorno;

    private T data;
}
