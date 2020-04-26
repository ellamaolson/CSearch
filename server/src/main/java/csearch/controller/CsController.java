package csearch.controller;

import csearch.model.Link;
import csearch.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import csearch.model.CsProject;
import csearch.repository.CsRepository;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CsController {

  @Autowired
  CsRepository repo;
  @Autowired
  LinkRepository linkrepo;

  @GetMapping("/all")
  public List<CsProject> index() {
    return (List<CsProject>) repo.findAll();
  }

  // needs to check for duplicates
  @PostMapping("/csproject")
  public CsProject create(@RequestBody Map<String, String> body) {
    String title = body.get("title");
    String description = body.get("description");
    String process = body.get("process");
    String difficultyString = body.get("difficulty");
    int difficulty = -1;
    if (difficultyString != null) {
      difficulty = Integer.parseInt(body.get("difficulty"));
    }
    CsProject project = new CsProject(title, description, process, difficulty);
    repo.save(project);
    String linksString = body.get("links");
    if (linksString != null) {
      String[] links = linksString.split(" ");
      for (String link : links) {
        linkrepo.save(new Link(link, project.getId()));
      }
    }
    return getProjectById(project.getId());
  }

  @GetMapping("/all/{id}")
  public CsProject getProjectById(@PathVariable(value = "id") Integer projectId) {
    CsProject project = repo.findById((projectId)).get();
    return project;
  }

  // needs to check for duplicates
  @PutMapping("/csproject/{id}")
  public CsProject update(@PathVariable(value = "id") Integer projectId, @RequestBody Map<String, String> body) {
    String title = body.get("title");
    String description = body.get("description");
    String process = body.get("process");
    String difficultyString = body.get("difficulty");
    CsProject project = repo.findById(projectId).get();
    if (title != null && !title.isEmpty()) project.setTitle(title);
    if (description != null) project.setDescription(description);
    if (process != null) project.setProcess(process);
    if (difficultyString != null) {
      int difficulty = Integer.parseInt(difficultyString);
      if (difficulty >= 1 && difficulty <= 5) project.setDifficulty(difficulty);
    }
    repo.save(project);
    String linksString = body.get("links");
    if (linksString != null) {
      String[] links = linksString.split(" ");
      for (String link : links) {
        linkrepo.save(new Link(link, projectId));
      }
    }
    return getProjectById(projectId);
  }
}
