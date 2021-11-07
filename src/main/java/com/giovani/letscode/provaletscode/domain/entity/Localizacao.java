package com.giovani.letscode.provaletscode.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TB_LOCALIZACAO")
public class Localizacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_LOCALIZACAO")
    private Long idLocalizacao;
    private String latitude;
    private String longitude;
    private String nomeGalaxia;

    @OneToMany(mappedBy = "localizacao",cascade = CascadeType.ALL)
    private List<Rebelde> rebeldes;


}
