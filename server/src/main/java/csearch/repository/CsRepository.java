package csearch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import csearch.model.CsProject;

@RepositoryRestResource
public interface CsRepository extends CrudRepository<CsProject, Integer>{
}
