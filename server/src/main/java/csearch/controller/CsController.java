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

  /**
   * Creates a Pair from a CsProject and associated list of Links
   * @param project
   * @return
   */
  private Pair createAPair(CsProject project) {
    List<Link> links = getProjectLinks(project.getId());
    return new Pair<CsProject,List<Link>> (project, links);
  }

  /**
   * Creates a list of Pairs
   * @param projects
   * @return
   */
  private List<Pair> createAPairList(List<CsProject> projects) {
    List<Pair> pairs = new ArrayList<Pair>();
    for(CsProject project: projects) {
      pairs.add(createAPair(project));
    }
    return pairs;
  }

  /**
   * Creates a new CsProject and associated Links
   * @param body
   * @return CsProject
   */
    @GetMapping
    public String hello() {
        return "Hello CSearch Dev Team With origin * with new update";
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

  /**
   * Get all projects in the database
   * @return
   */
  @GetMapping("/all")
  public List<Pair> index() {
    return createAPairList((List<CsProject>) this.csRepo.findAll());
  }

  @GetMapping("/all/{id}")
  public CsProject getProjectById(@PathVariable(value = "id") Integer projectId) {
    CsProject project = this.csRepo.findById((projectId)).get();
    return project;
  }

  /**
   * Search for projects based on a search term
   * @param searchTerm
   * @return List of Pairs of projects
   */
  @GetMapping("/search/{searchTerm}")
  public List<Pair> search(@PathVariable(value = "searchTerm") String searchTerm) {
    return createAPairList(this.csRepo.findProjectMatchingSearchTerm(searchTerm));
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
  public Pair<CsProject, List<Link>> getAllAboutProject(@PathVariable(value = "id") Integer pid) {
    CsProject project = getProjectById(pid);
    List<Link> links = getProjectLinks(pid);
    return new Pair<CsProject, List<Link>>(project, links);
  }

  @PutMapping("/csproject/{id}")
  public CsProject update(@PathVariable(value = "id") Integer projectId, @RequestBody Map<String, String> body) {
    String title = body.get("title");
    String description = body.get("description");
    String process = body.get("process");
    String difficultyString = body.get("difficulty");
    String linksString = body.get("links");

    CsProject project = this.csRepo.findById(projectId).get();

    project.setTitle(title);
    project.setDescription(description);
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
