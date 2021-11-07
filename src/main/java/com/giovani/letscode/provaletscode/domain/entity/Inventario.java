package com.giovani.letscode.provaletscode.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="TB_INVENTARIO")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String recurso;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name="ID_REBELDE", referencedColumnName="ID_REBELDE", nullable=false)
    private Rebelde rebelde;


}
