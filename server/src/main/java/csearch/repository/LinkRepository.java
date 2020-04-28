package csearch.repository;

import csearch.model.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository("lrepo")
public interface LinkRepository extends CrudRepository<Link, Integer> {

  @Query( value = "select id,link,pid from links where pid=:l_pid", nativeQuery = true)
  List<Link> findByPid(int l_pid);
  }
