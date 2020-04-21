package csearch.repository;

import csearch.model.CsProject;
import csearch.model.Link;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LinkRepository extends CrudRepository<Link, Integer> {

}
