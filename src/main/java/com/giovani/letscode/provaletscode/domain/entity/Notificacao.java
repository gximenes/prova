package com.giovani.letscode.provaletscode.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_NOTIFICACAO")
public class Notificacao implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_NOTIFICACAO")
    private Long idNotificacao;

    private String motivoNotificacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ID_REBELDE_DENUNCIANTE", referencedColumnName="ID_REBELDE", nullable=false)
    private Rebelde rebeldeDenunciante;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ID_REBELDE_DENUNCIADO", referencedColumnName="ID_REBELDE", nullable=false)
    private Rebelde rebeldeDenunciado;


}
