package com.giovani.letscode.provaletscode.application.service;


import com.giovani.letscode.provaletscode.application.repository.NotificacaoRepository;
import com.giovani.letscode.provaletscode.application.request.NotificacaoRequest;
import com.giovani.letscode.provaletscode.domain.entity.Notificacao;
import com.giovani.letscode.provaletscode.domain.entity.Rebelde;
import com.giovani.letscode.provaletscode.infrastructure.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Log4j2
public class NotificacaoService {

    @Autowired
    private RebeldeService rebeldeService;

    @Autowired
    private NotificacaoRepository notificacaoRepository;



    public void  inserirNotificacaoRebelde(@Valid NotificacaoRequest notificacaoTraidorRequest) throws BusinessException {

        Optional<Rebelde> rebeldeDenunciante =  rebeldeService.getRebeldeById(notificacaoTraidorRequest.getIdRebeldeDenunciante());
        Optional<Rebelde> rebeldeDenunciado =  rebeldeService.getRebeldeById(notificacaoTraidorRequest.getIdRebeldeDenunciado());

        if(!rebeldeDenunciante.isPresent()) {
            throw new BusinessException("Rebelde denunciante não encontrado para ser considerado um traidor");
        }

        if(!rebeldeDenunciado.isPresent()) {
            throw new BusinessException("Rebelde denunciado não encontrado para ser considerado um traidor");
        }

        Notificacao notificacao =  Notificacao.builder()
                .motivoNotificacao(notificacaoTraidorRequest.getMotivo())
                .rebeldeDenunciante(rebeldeDenunciante.get())
                .rebeldeDenunciado(rebeldeDenunciado.get()).build();
        try {
            notificacaoRepository.save(notificacao);

           List<Notificacao> notificacoes = notificacaoRepository.findAllByRebeldeDenunciado(rebeldeDenunciado.get());
           long quantidadeNotificacao = notificacoes.stream().filter(distinctByKey(Notificacao::getRebeldeDenunciante)).count();



           if(quantidadeNotificacao >= 3){
               rebeldeDenunciado.get().setTraidor(Boolean.TRUE);
               rebeldeService.inserirRebelde(rebeldeDenunciado.get());
           }

        }catch (Exception exception){
            log.error(exception);
            throw new BusinessException("Luke, eu sou seu pai e por isso teve uma falha ao salvar uma notificação!");
        }

    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
