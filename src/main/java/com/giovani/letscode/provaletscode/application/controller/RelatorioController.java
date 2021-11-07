package com.giovani.letscode.provaletscode.application.controller;


import com.giovani.letscode.provaletscode.application.Response.Response;
import com.giovani.letscode.provaletscode.application.service.RelatorioService;
import com.giovani.letscode.provaletscode.infrastructure.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
@Log4j2
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<Response> inserirRebelde(){

        Response rebeldeResponse = new Response();
        boolean hasErro = false;
        try {
            rebeldeResponse.setData(relatorioService.obterRelatorio());
            rebeldeResponse.setMensagemRetorno("Relatório da nave mãe emitido com sucesso!");
        }catch (BusinessException e){
            hasErro = Boolean.TRUE;
            rebeldeResponse.setMensagemRetorno(e.getMessage());
        }finally {
            return ResponseEntity.status( hasErro ? HttpStatus.INTERNAL_SERVER_ERROR :HttpStatus.OK).body(rebeldeResponse);
        }

    }

}



