package vitorsilverio.github.io.ibgedtb.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="municipio")
@Data
public class Municipio {

    @Id
    private Long id;

    private String nome;

    @Column(name="codigo_ibge")
    private Long codigoIBGE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="estado_id")
    private Estado estado;


}
