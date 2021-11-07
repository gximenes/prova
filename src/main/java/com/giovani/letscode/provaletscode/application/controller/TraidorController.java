package com.giovani.letscode.provaletscode.application.controller;


import com.giovani.letscode.provaletscode.application.Response.Response;
import com.giovani.letscode.provaletscode.application.request.NotificacaoRequest;
import com.giovani.letscode.provaletscode.application.service.NotificacaoService;
import com.giovani.letscode.provaletscode.infrastructure.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/traidores")
@Log4j2
public class TraidorController {


    @Autowired
    private NotificacaoService notificacaoRebeldeService;


    @PostMapping("/notificacao")
    public ResponseEntity<Response> inserirNotificacaoTraidor(@Valid @RequestBody NotificacaoRequest notificacaoTraidorRequest){

        Response rebeldeResponse = new Response();
        boolean hasErro = false;
        try {

            notificacaoRebeldeService.inserirNotificacaoRebelde(notificacaoTraidorRequest);
            rebeldeResponse.setMensagemRetorno("Alerta de traição incluído com sucesso. O império não irá contra-atacar.");
        }catch (BusinessException e){
            hasErro = Boolean.TRUE;
            rebeldeResponse.setMensagemRetorno(e.getMessage());
        }finally {
            return ResponseEntity.status( hasErro ? HttpStatus.INTERNAL_SERVER_ERROR :HttpStatus.CREATED).body(rebeldeResponse);
        }

    }





}
