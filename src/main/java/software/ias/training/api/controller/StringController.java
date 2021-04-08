package software.ias.training.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/srcString")
public class StringController {
    @GetMapping
    public String stringController(){
        return "Hello World";
    }
}
