package csearch.controller;

import csearch.model.CsProject;
import csearch.repository.CsRepository;
import csearch.service.CsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class CsController {
    private CsService csService;

    @Autowired
    public CsController(CsService css) {
      this.csService = css;
    }

//    @Autowired
//    private CsRepository repo;

    @GetMapping
    public String hello() {
        return "hello Elana";
    }

    @GetMapping("/all")
    public List<CsProject> index() {
      return (List<CsProject>) this.csService.findAll();
    }

    @GetMapping("/search/{searchTerm}")
    public List<CsProject> search(@PathVariable(value = "searchTerm") String searchTerm) {
      return (List<CsProject>) this.csService.findProjectMatchingSearchTerm(searchTerm);
    }

    @PostMapping("/csproject")
    public CsProject create(@RequestBody Map<String, String> body) {
      String title = body.get("title");
      String description = body.get("description");
      String process = body.get("process");
      int difficulty = Integer.parseInt(body.get("difficulty"));
//      String[] links = body.get("links").split(" ");
      return this.csService.save(new CsProject(title, description, process, difficulty));
    }
}
