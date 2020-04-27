package csearch.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import csearch.model.CsProject;
import org.springframework.stereotype.Repository;

@Repository
public interface CsRepository extends CrudRepository<CsProject, Integer>{

    @Query("SELECT cs FROM csprojects cs WHERE cs.title LIKE '%:searchTerm%' OR cs.description LIKE '%:searchTerm%' OR cs.process LIKE '%:searchTerm%'")
    List<CsProject> findProjectMatchingSearchTerm(String searchTerm);
}
