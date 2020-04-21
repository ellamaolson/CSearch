package csearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import csearch.model.CsProject;
import csearch.repository.RestRepository;

import javax.swing.*;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {

    @Autowired
    RestRepository repo;

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

  @PostMapping("/csproject")
  public String create(@RequestBody Map<String, String> body) {
    String title = body.get("title");
    String description = body.get("description");
    String process = body.get("process");
    int difficulty = Integer.parseInt(body.get("difficulty"));
//      String[] links = body.get("links").split(" ");
    String links = body.get("links");
    CsProject project = new CsProject(title, description, process, difficulty);
    project.setLinks(links);
    repo.save(project);
    return "Project " + project.getTitle() + " created successfully";
  }



    @GetMapping("/all/{id}")
    public   CsProject getProjectById(@PathVariable(value = "id") Integer projectId){
      CsProject project = repo.findById((projectId)).get();
      return project;
      }

  @PostMapping("/csproject/{id}")
    public CsProject update(@PathVariable(value = "id") Integer projectId, @RequestBody Map<String, String> body){
      String title = body.get("title");
      String description = body.get("description");
      String process = body.get("process");
      int difficulty = Integer.parseInt(body.get("difficulty"));
      CsProject project = repo.findById(projectId).get();
      if( title != null) project.setTitle(title);
      if( description != null) project.setDescription(description);
      if( process != null) project.setProcess(process);
      if( difficulty != 0 ) project.setDifficulty(difficulty); // ?

      return repo.save(project);
    }





}
