package csearch.repository;

import csearch.model.CsProject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Repository("csrepo")
public interface CsRepository extends CrudRepository<CsProject, Integer>{
      @Query(value = "SELECT pid, title, description, process, difficulty FROM csprojects cs WHERE cs.title LIKE " +
        "%:searchTerm% OR cs.description LIKE %:searchTerm% OR cs.process LIKE %:searchTerm%", nativeQuery = true)
      List<CsProject> findProjectMatchingSearchTerm(String searchTerm);

}
