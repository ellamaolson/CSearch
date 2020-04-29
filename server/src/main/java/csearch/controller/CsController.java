package csearch.controller;

import csearch.model.Link;
import csearch.repository.LinkRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import csearch.model.CsProject;
import csearch.repository.CsRepository;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CsController {
  private final CsRepository csRepo;
  private final LinkRepository lrepo;

  @Autowired
  public CsController(@Qualifier("csrepo") CsRepository repo, @Qualifier("lrepo") LinkRepository linkrepo) {
    this.csRepo = repo;
    this.lrepo = linkrepo;
  }

  @PostMapping("/csproject")
  public CsProject create(@RequestBody Map<String, String> body) {
    String title = body.get("title");
    String description = body.get("description");
    String process = body.get("process");
    String difficultyString = body.get("difficulty");
    String linksString = body.get("links");

    int difficulty = -1;
    if (difficultyString != null) {
      difficulty = Integer.parseInt(body.get("difficulty"));
    }

    CsProject project = new CsProject(title, description, process, difficulty);
    this.csRepo.save(project);

    if (linksString != null) {
      String[] links = linksString.split(" ");
      for (String link : links) {
        this.lrepo.save(new Link(link, project.getId()));
      }
    }
    return getProjectById(project.getId());
  }

  @GetMapping("/all")
  public List<CsProject> index() {
    return (List<CsProject>) this.csRepo.findAll();
  }

  @GetMapping("/all/{id}")
  public CsProject getProjectById(@PathVariable(value = "id") Integer projectId) {
    CsProject project = this.csRepo.findById((projectId)).get();
    return project;
  }

  @GetMapping("/search/{searchTerm}")
  public List<CsProject> search(@PathVariable(value = "searchTerm") String searchTerm) {
    return (List<CsProject>) this.csRepo.findProjectMatchingSearchTerm(searchTerm);
  }

  @GetMapping("/links")
  public List<Link> getLinks() {
    return (List<Link>) this.lrepo.findAll();
  }

  @GetMapping("/links/{pid}")
  public List<Link> getProjectLinks(@PathVariable(value = "pid") Integer pid) {
    System.out.println(pid);
    return (List<Link>) this.lrepo.findByPid(pid);
  }

  @GetMapping("/about/{id}")
  public Pair<CsProject, List<Link>> getAllAboutProject(@PathVariable(value = "id") Integer pid){
    CsProject project = getProjectById(pid);
    List<Link> links = getProjectLinks(pid);
    return new Pair<CsProject,List<Link>> (project,links);
  }

  @PutMapping("/csproject/{id}")
  public CsProject update(@PathVariable(value = "id") Integer projectId, @RequestBody Map<String, String> body) {
    String title = body.get("title");
    String description = body.get("description");
    String process = body.get("process");
    String difficultyString = body.get("difficulty");
    String linksString = body.get("links");

    CsProject project = this.csRepo.findById(projectId).get();

    if (title != null && !title.isEmpty())
      project.setTitle(title);
    if (description != null)
      project.setDescription(description);
    if (process != null)
      project.setProcess(process);
    if (difficultyString != null) {
      int difficulty = Integer.parseInt(difficultyString);
      if (difficulty >= 1 && difficulty <= 5)
        project.setDifficulty(difficulty);
    }
    this.csRepo.save(project);

    if (linksString != null) {
      String[] links = linksString.split(" ");
      for (String link : links) {
        this.lrepo.save(new Link(link, projectId));
      }
    }
    return getProjectById(projectId);
  }
}
