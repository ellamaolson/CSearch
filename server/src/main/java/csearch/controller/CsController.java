package csearch.controller;

import csearch.model.CsProject;
import csearch.repository.CsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CsController {
    private final CsRepository csRepo;

    @Autowired
    public CsController(@Qualifier("csrepo") CsRepository repo) {
      this.csRepo = repo;
    }

    @GetMapping
    public String hello() {
        return "hello Elana";
    }

    @GetMapping("/all")
    public List<CsProject> index() {
      return (List<CsProject>) this.csRepo.findAll();
    }

    @GetMapping("/search/{searchTerm}")
    public List<CsProject> search(@PathVariable(value = "searchTerm") String searchTerm) {
      return (List<CsProject>) this.csRepo.findProjectMatchingSearchTerm(searchTerm);
    }

    @PostMapping("/csproject")
    public CsProject create(@RequestBody Map<String, String> body) {
      String title = body.get("title");
      String description = body.get("description");
      String process = body.get("process");
      int difficulty = Integer.parseInt(body.get("difficulty"));
//      String[] links = body.get("links").split(" ");
      return this.csRepo.save(new CsProject(title, description, process, difficulty));
    }
}
