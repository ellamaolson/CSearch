package csearch.repository;


import csearch.model.Link;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.stream.Stream;
@Repository("lrepo")
public interface LinkRepository extends CrudRepository<Link, Integer> {

  @Query( value = "select id,link,pid from links where pid=:l_pid", nativeQuery = true)
  List<Link> findByPid(int l_pid);
  }
