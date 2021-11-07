package com.giovani.letscode.provaletscode.application.service;


import com.giovani.letscode.provaletscode.application.repository.LocalizacaoRebeldeRepository;
import com.giovani.letscode.provaletscode.application.repository.RebeldeRepository;
import com.giovani.letscode.provaletscode.application.request.RebeldeRequest;
import com.giovani.letscode.provaletscode.domain.entity.Localizacao;
import com.giovani.letscode.provaletscode.domain.entity.Rebelde;
import com.giovani.letscode.provaletscode.domain.enuns.Genero;
import com.giovani.letscode.provaletscode.domain.enuns.Recurso;
import com.giovani.letscode.provaletscode.infrastructure.Util.StringUtil;
import com.giovani.letscode.provaletscode.infrastructure.exception.BusinessException;
import com.giovani.letscode.provaletscode.infrastructure.rowMapper.InventarioRowMapper;
import com.giovani.letscode.provaletscode.infrastructure.rowMapper.LocalizacaoRowMapper;
import com.giovani.letscode.provaletscode.infrastructure.rowMapper.RebeldeRowMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
public class RebeldeService {

    @Autowired
    private RebeldeRepository rebeldeRepository;


    @Autowired
    private LocalizacaoRebeldeRepository localizacaoRepository;

    public void inserirRebeldeRequest(RebeldeRequest rebeldeRequest) throws BusinessException {

        isGeneroValido(rebeldeRequest.getGenero());

        try {
            Rebelde rebelde = RebeldeRowMapper.mapRow(rebeldeRequest);
            isRecursosValidos(rebeldeRequest);
            rebelde.setInventarios(InventarioRowMapper.mapRow(rebeldeRequest.getInventarios(),rebelde));
            List<Rebelde> rebeldes = new ArrayList<>();
            rebeldes.add(rebelde);
            rebelde.setLocalizacao( LocalizacaoRowMapper.mapRow(rebeldeRequest,rebeldes));
            rebeldeRepository.save(rebelde);
        }catch (Exception e){
            log.error(e);
            throw new  BusinessException("Falha ao inserir rebelde: consultar a equipe Millennium Falcon para mais informações ");
        }

    }

    public List<Map<String, Integer>> getRecursosAgrupados(){
        return rebeldeRepository.getRecursoAgrupado();
    }

    public Integer getPontosTraidores(){
        return rebeldeRepository.getPontosTraidores();
    }

    public void inserirRebelde(Rebelde rebelde) throws BusinessException {

        try {

            rebeldeRepository.save(rebelde);
        }catch (Exception e){
            log.error(e);
            throw new  BusinessException("Falha ao inserir rebelde: consultar a equipe Millennium Falcon para mais informações ");
        }

    }






    private void isGeneroValido(String generoDescricao) throws BusinessException {
        Genero genero  = Genero.getGeneroByDescription(generoDescricao);
        if(genero == null){
            throw  new BusinessException("Genero inválido, informar as seguintes opções: "+Genero.values());
        }
    }

    public  Optional<Rebelde> getRebeldeById(Long idRebelde){
        return rebeldeRepository.findById(idRebelde);
    }

    public void atualizarRebelde(RebeldeRequest rebeldeRequest) throws BusinessException {

        isLocalizacaoValida(rebeldeRequest);
        Optional<Rebelde> rebelde =  getRebeldeById(rebeldeRequest.getIdRebelde());
        if(!rebelde.isPresent()) {
            throw new BusinessException("Rebelde não encontrado para realizar atualização");
        }

        try {

            Long idLocalizacao =  rebelde.get().getLocalizacao().getIdLocalizacao();


            localizacaoRepository.save(Localizacao.builder().idLocalizacao(idLocalizacao)
                    .nomeGalaxia(rebeldeRequest.getNomeGalaxia())
                    .longitude(rebeldeRequest.getLongitude())
                    .latitude(rebeldeRequest.getLatitude()).build());

        }catch (Exception e){
            log.error(e);
            throw new  BusinessException("Falha ao atualizar rebelde: consultar a equipe Millennium Falcon para mais informações ");
        }

    }

    private void isRecursosValidos(RebeldeRequest rebeldeRequest)  throws BusinessException{

        for (String desc : rebeldeRequest.getInventarios().keySet()) {
            Recurso rec = Recurso.recursoByDescricao(desc);
            if(rec == null){
                throw new BusinessException ("Recurso inválido, segue os aprovados pelo Luke Skywalker: "+Recurso.descricoes());
            }
        }


    }

    private void isLocalizacaoValida(RebeldeRequest rebeldeRequest) throws BusinessException {

        if(StringUtil.isVaziaOuNula(rebeldeRequest.getLongitude()) || StringUtil.isVaziaOuNula(rebeldeRequest.getLatitude())
                || StringUtil.isVaziaOuNula(rebeldeRequest.getNomeGalaxia()) || rebeldeRequest.getIdRebelde() == null || rebeldeRequest.getIdRebelde().longValue() == 0){
            throw  new BusinessException("Falha no sistema de atualização: os campos Lontidudes, Latitudes , Nome galáxia e identificador do rebelde são obrigatórios ");
        }
    }

    public long getCountTotalTraidor(Boolean traidor) {

        return rebeldeRepository.countBytraidor(traidor);
    }

    public long getCountTotal() {

        return rebeldeRepository.count();
    }
}
