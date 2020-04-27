package csearch.service;

import csearch.model.CsProject;
import csearch.service.ICsService;
import csearch.repository.CsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CsService implements ICsService {

  private CsRepository repo;

  @Autowired
  public CsService(@Qualifier("repo") CsRepository repo) {
    this.repo = repo;
  }

  @Override
  @Transactional
  public List<CsProject> findProjectMatchingSearchTerm(String searchTerm) {
    return (List<CsProject>) this.repo.findProjectMatchingSearchTerm(searchTerm);
  }

  @Override
  @Transactional
  public CsProject save(CsProject project) {
    return this.repo.save(project);
  }

  @Override
  @Transactional
  public List<CsProject> findAll() {
    return (List<CsProject>) this.repo.findAll();
  }
}
