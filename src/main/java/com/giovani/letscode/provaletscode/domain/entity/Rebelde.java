package com.giovani.letscode.provaletscode.domain.entity;

import com.giovani.letscode.provaletscode.domain.enuns.Genero;
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
@Table(name = "TB_REBELDE")
public class Rebelde implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_REBELDE")
    private Long idRebelde;

    private String nome;

    private Integer idade;

    private Genero genero;

    @Column(columnDefinition = "boolean default false")
    private Boolean traidor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ID_LOCALIZACAO", referencedColumnName="ID_LOCALIZACAO", nullable=false)
    private Localizacao localizacao;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "rebelde",fetch = FetchType.LAZY)
    private List<Inventario> inventarios;


}
