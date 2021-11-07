package com.giovani.letscode.provaletscode.application.service;


import com.giovani.letscode.provaletscode.application.Response.RelatorioResponse;
import com.giovani.letscode.provaletscode.infrastructure.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class RelatorioService {


    @Autowired
    private RebeldeService rebeldeService;

    public RelatorioResponse obterRelatorio() throws BusinessException {

        try {

            Long qtdTotalRebelde = rebeldeService.getCountTotal();
            Long qtdTotalTraidor = rebeldeService.getCountTotalTraidor(Boolean.TRUE);


            BigDecimal porcentagemTraidor = BigDecimal.valueOf( (double)qtdTotalTraidor.intValue() / qtdTotalRebelde.intValue() * 100);
            BigDecimal porcentagemRebelde = BigDecimal.valueOf(((double)(qtdTotalRebelde.intValue() - qtdTotalTraidor.intValue()) / qtdTotalRebelde) * 100);


            List<Map<String, Integer>> agrupado = rebeldeService.getRecursosAgrupados();
            Integer qtdPontosTraidores = rebeldeService.getPontosTraidores();

            return RelatorioResponse.builder()
                    .porcentagemRebelde(porcentagemRebelde)
                    .porcentagemTraidor(porcentagemTraidor)
                    .qtdPontosTraidores(qtdPontosTraidores)
                    .recursos(agrupado).build();

        }catch (Exception e){
            log.error(e);
            throw new BusinessException(" Chewbacca Informa que aconteceu uma exceção ao retornar relatório ");
        }
    }
}
