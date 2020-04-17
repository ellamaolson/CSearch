package csearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import csearch.model.CsProject;
import csearch.repository.CsRepository;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CsController {

    @Autowired
    CsRepository repo;

    @GetMapping
    public String hello() {
        return "hello Elana";
    }

    @GetMapping("/all")
    public List<CsProject> index() {
      return (List<CsProject>) repo.findAll();
    }

    @PostMapping("/csproject")
    public CsProject create(@RequestBody Map<String, String> body) {
      String title = body.get("title");
      String description = body.get("description");
      String process = body.get("process");
      int difficulty = Integer.parseInt(body.get("difficulty"));
//      String[] links = body.get("links").split(" ");
      return repo.save(new CsProject(title, description, process, difficulty));
    }
}
