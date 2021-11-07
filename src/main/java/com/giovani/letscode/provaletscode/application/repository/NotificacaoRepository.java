package com.giovani.letscode.provaletscode.application.repository;

import com.giovani.letscode.provaletscode.domain.entity.Localizacao;
import com.giovani.letscode.provaletscode.domain.entity.Notificacao;
import com.giovani.letscode.provaletscode.domain.entity.Rebelde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

     List<Notificacao> findAllByRebeldeDenunciado(Rebelde rebeldeDenunciado);


}
