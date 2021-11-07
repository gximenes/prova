package com.giovani.letscode.provaletscode.domain.enuns;

import lombok.Getter;

@Getter
public enum Genero {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTRO("Outro");

    private Genero(String genero){
        this.descricao = genero;
    }

    private String descricao;

    public static Genero getGeneroByDescription(String descricao){

        for(Genero genero : Genero.values()){
            if(genero.getDescricao().toLowerCase().equals(descricao.trim().toLowerCase())){
                return genero;
            }
        }

        return null;
    }

}
