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
    LinkRepository  linkrepo;

    @GetMapping
    public String hello() {
        return "hello Elana";
    }

    @GetMapping("/all")
    public List<CsProject> index() {
      return (List<CsProject>) repo.findAll();
    }

//    @PostMapping("/csproject")
//    public CsProject create(@RequestBody Map<String, String> body) {
//      String title = body.get("title");
//      String description = body.get("description");
//      String process = body.get("process");
//      int difficulty = Integer.parseInt(body.get("difficulty"));
////      String[] links = body.get("links").split(" ");
//      return repo.save(new CsProject(title, description, process, difficulty));
//    }

  // needs to check for duplicates
  @PostMapping("/csproject")
  public String create(@RequestBody Map<String, String> body) {
    String title = body.get("title");
    String description = body.get("description");
    String process = body.get("process");
    int difficulty = Integer.parseInt(body.get("difficulty"));
    String[] links = body.get("links").split(" ");
    CsProject project = new CsProject(title, description, process, difficulty);
    repo.save(project);
    for (String l: links)
      linkrepo.save(new Link(l, project.getId()));

    return "Project " + project.getTitle() + " created successfully";
  }

    @GetMapping("/all/{id}")
    public   CsProject getProjectById(@PathVariable(value = "id") Integer projectId){
      CsProject project = repo.findById((projectId)).get();
      return project;
      }

      // needs to check for duplicates
  @PostMapping("/csproject/{id}")
    public String update(@PathVariable(value = "id") Integer projectId, @RequestBody Map<String, String> body){
      String title = body.get("title");
      String description = body.get("description");
      String process = body.get("process");
      int difficulty = Integer.parseInt(body.get("difficulty"));
      CsProject project = repo.findById(projectId).get();
      if( title != null) project.setTitle(title);
      if( description != null) project.setDescription(description);
      if( process != null) project.setProcess(process);
      if( difficulty != 0 ) project.setDifficulty(difficulty); // ? breaks if the field missing
      repo.save(project);
      String[] links = body.get("links").split(" ");

      for (String l: links){
          linkrepo.save(new Link(l, projectId));
      }

      return "Project " + project.getTitle() + " updated successfully";
    }





}
