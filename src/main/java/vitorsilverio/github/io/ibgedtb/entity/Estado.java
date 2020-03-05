package vitorsilverio.github.io.ibgedtb.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="estado")
@Data
public class Estado {

    @Id
    private Integer id;

    private String nome;

    private String sigla;

    @Column(name="codigo_ibge")
    private Long codigoIBGE;

    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Municipio> municipios;


}
