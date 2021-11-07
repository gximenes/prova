package com.giovani.letscode.provaletscode.application.Response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Builder
@EqualsAndHashCode
@Getter
public class RelatorioResponse implements Serializable {

    private BigDecimal porcentagemTraidor ;
    private BigDecimal porcentagemRebelde ;
    private List<Map<String, Integer>> recursos;
    private Integer qtdPontosTraidores;

}
