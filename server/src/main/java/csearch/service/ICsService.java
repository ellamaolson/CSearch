package csearch.service;

import csearch.model.CsProject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICsService {
  List<CsProject> findProjectMatchingSearchTerm(String searchTerm);
  CsProject save(CsProject project);
  List<CsProject> findAll();
}
