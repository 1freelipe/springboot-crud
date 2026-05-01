package br.com.fatecararas.dicasdevspring.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
    
    @GetMapping
    public String hello() {
        System.out.println("Passou pelo controller");
        return "Hello Controller";
    }
}
