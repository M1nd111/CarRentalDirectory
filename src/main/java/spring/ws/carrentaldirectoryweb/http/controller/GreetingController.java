package spring.ws.carrentaldirectoryweb.http.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/api")
public class GreetingController {

//    @ModelAttribute("roles")
//    public List<Role> getRole(){
//        return Arrays.asList(Role.values());
//    }
//
//
//    @GetMapping("/hello")
//    public String hello(
//            @RequestParam("age") Integer age,
//            @RequestHeader("accept") String accept,
//            Model model,
//            UserReadDto userReadDto){
//        model.addAttribute("user", userReadDto);
//        return "learning/hello";
//    }
//
//    @GetMapping("/bye")
//    public String bye(){
//        return "/learning/bye";
//    }

}
