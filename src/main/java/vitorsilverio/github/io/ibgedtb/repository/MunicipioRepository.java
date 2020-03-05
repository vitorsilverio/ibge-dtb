package vitorsilverio.github.io.ibgedtb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import vitorsilverio.github.io.ibgedtb.entity.Municipio;

import java.util.List;

public interface MunicipioRepository extends PagingAndSortingRepository<Municipio, Long> {

    @RestResource(path = "nome_e_estado")
    @Query("select m from Municipio m join m.estado e where upper(m.nome) = upper(:nomeCidade) and upper(e.sigla) = upper(:siglaEstado)")
    public List<Municipio> procuraPorMunicipioEEstado(@Param("nomeCidade") String Nome, @Param("siglaEstado") String siglaEstado);
}
