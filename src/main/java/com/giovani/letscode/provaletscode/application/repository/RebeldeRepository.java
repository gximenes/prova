package com.giovani.letscode.provaletscode.application.repository;

import com.giovani.letscode.provaletscode.domain.entity.Rebelde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface RebeldeRepository  extends  JpaRepository<Rebelde, Long> {


    long countBytraidor(boolean traidor);

    @Query(value = "select  inv.recurso, sum(inv.quantidade) as quantidade    from tb_inventario inv inner join   TB_REBELDE  rb  on (rb.id_rebelde = inv. id_rebelde  and (traidor = false or traidor is null)) group by inv.recurso ",
            nativeQuery = true)
    List<Map<String, Integer>> getRecursoAgrupado();




    @Query(value = "select  sum(inv.quantidade)  as quantidade    from tb_inventario inv inner join   TB_REBELDE  rb  on (rb.id_rebelde = inv. id_rebelde  and traidor = true) ",
            nativeQuery = true)
    Integer getPontosTraidores();

}
