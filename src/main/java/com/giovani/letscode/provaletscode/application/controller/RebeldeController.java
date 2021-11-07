package com.giovani.letscode.provaletscode.application.controller;


import com.giovani.letscode.provaletscode.application.Response.Response;
import com.giovani.letscode.provaletscode.application.request.NegociacaoRequest;
import com.giovani.letscode.provaletscode.application.request.RebeldeRequest;
import com.giovani.letscode.provaletscode.application.service.NegociacaoService;
import com.giovani.letscode.provaletscode.application.service.RebeldeService;
import com.giovani.letscode.provaletscode.infrastructure.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rebeldes")
@Log4j2
public class RebeldeController {


    @Autowired
    private RebeldeService rebeldeService;

    @Autowired
    private NegociacaoService negociacaoService;


    @PostMapping
    public ResponseEntity<Response> inserirRebelde(@Valid @RequestBody RebeldeRequest rebeldeRequest){

        Response rebeldeResponse = new Response();
        boolean hasErro = false;
        try {

            rebeldeService.inserirRebeldeRequest(rebeldeRequest);
            rebeldeResponse.setMensagemRetorno("Rebelde criado com sucesso");
        }catch (BusinessException e){
            hasErro = Boolean.TRUE;
            rebeldeResponse.setMensagemRetorno(e.getMessage());
        }finally {
            return ResponseEntity.status( hasErro ? HttpStatus.INTERNAL_SERVER_ERROR :HttpStatus.CREATED).body(rebeldeResponse);
        }

    }


    @PutMapping
    public ResponseEntity<Response> atualizarRebelde(@RequestBody RebeldeRequest rebeldeRequest){

        Response rebeldeResponse = new Response();
        boolean hasErro = false;
        try {

            rebeldeService.atualizarRebelde(rebeldeRequest);
            rebeldeResponse.setMensagemRetorno("Rebelde atualizado com sucesso");
        }catch (BusinessException e){
            hasErro = Boolean.TRUE;
            rebeldeResponse.setMensagemRetorno(e.getMessage());
        }finally {
            return ResponseEntity.status( hasErro ? HttpStatus.INTERNAL_SERVER_ERROR :HttpStatus.OK).body(rebeldeResponse);
        }

    }


    @PutMapping("/negociacao")
    public ResponseEntity<Response> negociarItensRebeldes(@RequestBody NegociacaoRequest negociacaoRequest) {
        Response rebeldeResponse = new Response();
        boolean hasErro = false;
        try {

            negociacaoService.negociarItenRebeldes(negociacaoRequest);
            rebeldeResponse.setMensagemRetorno("Negociação realizada com sucesso.");
        }catch (BusinessException e){
            hasErro = Boolean.TRUE;
            rebeldeResponse.setMensagemRetorno(e.getMessage());
        }finally {
            return ResponseEntity.status( hasErro ? HttpStatus.INTERNAL_SERVER_ERROR :HttpStatus.OK).body(rebeldeResponse);
        }


    }



}
