package com.giovani.letscode.provaletscode.application.service;


import com.giovani.letscode.provaletscode.application.request.NegociacaoRequest;
import com.giovani.letscode.provaletscode.application.request.RecursoRequest;
import com.giovani.letscode.provaletscode.domain.entity.Inventario;
import com.giovani.letscode.provaletscode.domain.entity.Rebelde;
import com.giovani.letscode.provaletscode.domain.enuns.Recurso;
import com.giovani.letscode.provaletscode.infrastructure.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class NegociacaoService {

    @Autowired
    private RebeldeService rebeldeService;



    public void negociarItenRebeldes(NegociacaoRequest negociacaoRequest) throws BusinessException {
        Optional<Rebelde> rebeldeSolicitante = rebeldeService.getRebeldeById(negociacaoRequest.getIdRebeldeSolicitante());
        Optional<Rebelde> rebeldeSolicitado = rebeldeService.getRebeldeById(negociacaoRequest.getIdRebeldeSolicitado());

        if(rebeldeSolicitante.get().getTraidor() == Boolean.TRUE ||  rebeldeSolicitado.get().getTraidor() == Boolean.TRUE ){
            throw new BusinessException("Existe um traidor entre nós, o lado escuro da força tomou posse dos rebeldes. Negociação não permitida");
        }

        if (!rebeldeSolicitante.isPresent()) {
            throw new BusinessException("Rebelde denunciante não encontrado para ser considerado um traidor");
        }

        if (!rebeldeSolicitado.isPresent()) {
            throw new BusinessException("Rebelde denunciado não encontrado para ser considerado um traidor");
        }

        validarRecursoQuantidadePresente(rebeldeSolicitante.get().getInventarios(),negociacaoRequest.getRecursosSolicitante());
        validarRecursoQuantidadePresente(rebeldeSolicitado.get().getInventarios(),negociacaoRequest.getRecursosSolicitado());

        validarQuantidadePontos(negociacaoRequest.getRecursosSolicitado(),negociacaoRequest.getRecursosSolicitante());

        salvarNegociacao(negociacaoRequest,rebeldeSolicitante.get(),rebeldeSolicitado.get());

    }

    private void salvarNegociacao(NegociacaoRequest negociacaoRequest,Rebelde rebeldeSolicitante , Rebelde rebeldeSolicitado) throws BusinessException {

        try{

            for(RecursoRequest recurso : negociacaoRequest.getRecursosSolicitante()){
                 int cont = 0;
                  for(Inventario inventario: rebeldeSolicitado.getInventarios()){
                       if(recurso.getNome().equalsIgnoreCase(inventario.getRecurso())){
                           cont ++;
                           inventario.setRecurso(recurso.getNome());
                           inventario.setQuantidade(recurso.getQuantidade());
                       }
                  }

                for(Inventario inventario: rebeldeSolicitante.getInventarios()){
                    if(recurso.getNome().equalsIgnoreCase(inventario.getRecurso())){
                        inventario.setRecurso(recurso.getNome());
                        inventario.setQuantidade(inventario.getQuantidade() - recurso.getQuantidade());
                    }
                }

                  if(cont == 0){
                      rebeldeSolicitado.getInventarios().add(Inventario.builder().recurso(recurso.getNome())
                              .quantidade(recurso.getQuantidade())
                              .rebelde(rebeldeSolicitado).build());
                  }
            }

            for(RecursoRequest recurso : negociacaoRequest.getRecursosSolicitado()){
                int cont = 0;
                for(Inventario inventario: rebeldeSolicitante.getInventarios()){
                    if(recurso.getNome().equalsIgnoreCase(inventario.getRecurso())){
                        cont ++;
                        inventario.setRecurso(recurso.getNome());
                        inventario.setQuantidade(recurso.getQuantidade());
                    }
                }
                for(Inventario inventario: rebeldeSolicitado.getInventarios()){
                    if(recurso.getNome().equalsIgnoreCase(inventario.getRecurso())){
                        inventario.setRecurso(recurso.getNome());
                        inventario.setQuantidade(inventario.getQuantidade() - recurso.getQuantidade());
                    }
                }

                if(cont == 0){
                    rebeldeSolicitado.getInventarios().add(Inventario.builder().recurso(recurso.getNome())
                            .quantidade(recurso.getQuantidade())
                            .rebelde(rebeldeSolicitante).build());
                }
            }


            rebeldeService.inserirRebelde(rebeldeSolicitado);
            rebeldeService.inserirRebelde(rebeldeSolicitante);

        }catch (Exception e){
            log.error(e);
            throw new BusinessException(" A estrela da morte gerou uma exceção ao salvar a negociação, procure o Han Solo para mais informações");
        }
    }

    private void validarQuantidadePontos(List<RecursoRequest> recursosSolicitante , List<RecursoRequest> recursosSolicitado) throws BusinessException {

        int pontosSolicitante = 0;
        int pontosSolicitado = 0;

        for (RecursoRequest recurso : recursosSolicitante){
            pontosSolicitante += (recurso.getQuantidade() * Recurso.pontoByDescricao(recurso.getNome()));
        }


        for (RecursoRequest recurso : recursosSolicitado){
            pontosSolicitado += (recurso.getQuantidade() * Recurso.pontoByDescricao(recurso.getNome()));
        }

        if(pontosSolicitante !=  pontosSolicitado){
            throw  new BusinessException("Quantidade de pontos não equivalentes");
        }


    }

    private void validarRecursoQuantidadePresente(List<Inventario> inventarios, List<RecursoRequest> recursosRequest) throws BusinessException {

        for(RecursoRequest inventarioRequest :recursosRequest ){
            int cont = 0;
            for(Inventario inventario : inventarios){
                if (inventarioRequest.getNome().equalsIgnoreCase(inventario.getRecurso())) {
                    cont ++;
                    if (inventarioRequest.getQuantidade() > inventario.getQuantidade()) {
                        throw  new BusinessException("Rebelde não pode negociar mais item do que possui em seu inventário.");
                    }
                }
            }
            if (cont == 0) {
                throw  new BusinessException("Rebelde não possui recurso em seu inventário.");
            }

        }
    }


}
