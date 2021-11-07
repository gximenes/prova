package com.giovani.letscode.provaletscode.domain.enuns;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum Recurso {

    AGUA("Agua",2),
    ARMA("Arma",4),
    COMIDA("Comida",1),
    MUNICAO("Munição",3);

     private Recurso(String desc,Integer ponto)  {
        this.pontuacao = ponto;
        this.descricao = desc;
     }

    private Integer pontuacao;
    private String  descricao;

    public static int pontoByDescricao(String descricao){
        for(Recurso rec : Recurso.values()){
            if(rec.getDescricao().equalsIgnoreCase(descricao.trim())){
                return rec.getPontuacao();
            }
        }
        return 0;
    }

    public static Recurso recursoByDescricao(String descricao){
        for(Recurso rec : Recurso.values()){
            if(rec.getDescricao().equalsIgnoreCase(descricao.trim())){
                return rec;
            }
        }
        return null;
    }


    public static List<String> descricoes(){

        List<String> descricoes = new ArrayList<>();
        for(Recurso rec : Recurso.values()){
            descricoes.add(rec.getDescricao());
        }
        return descricoes;
    }


}
