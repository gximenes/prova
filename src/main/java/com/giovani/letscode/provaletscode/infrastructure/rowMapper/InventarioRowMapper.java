package com.giovani.letscode.provaletscode.infrastructure.rowMapper;

import com.giovani.letscode.provaletscode.domain.entity.Inventario;
import com.giovani.letscode.provaletscode.domain.entity.Rebelde;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventarioRowMapper {

    public static List<Inventario> mapRow(Map<String,Integer> inventariosRequest, Rebelde rebelde)  {

        List<Inventario> inventarios = new ArrayList<>();

        inventariosRequest.forEach( (k,v) ->{
            inventarios.add(Inventario.builder().recurso(k).quantidade(v).rebelde(rebelde).build());
        });



        return inventarios;
    }
}
