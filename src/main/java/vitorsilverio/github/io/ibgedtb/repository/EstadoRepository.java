package vitorsilverio.github.io.ibgedtb.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import vitorsilverio.github.io.ibgedtb.entity.Estado;

@RepositoryRestResource(collectionResourceRel = "estados", path = "estados")
public interface EstadoRepository extends PagingAndSortingRepository<Estado,Integer> {

    @RestResource(path = "sigla")
    public Estado findBySigla(String sigla);

}
