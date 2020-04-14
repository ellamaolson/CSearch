package csearch.controller;


import csearch.repository.RestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

//  @Autowired
//  RestRepository repo;

    @GetMapping
    public String hello() {
        return "hello Elana";
    }
}
