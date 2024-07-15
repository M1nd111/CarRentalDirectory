package spring.ws.carrentaldirectoryweb.http.controller;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.ws.carrentaldirectoryweb.core.repository.RecordRepository;
import spring.ws.carrentaldirectoryweb.core.service.RecordService;
import spring.ws.carrentaldirectoryweb.sd.redBlackTree.RedBlackTree;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    RecordService recordService;
    @Autowired
    RecordRepository recordRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String findAll(Model model, HttpSession httpSession){
        var records =  recordService.findAll();
        model.addAttribute("records", records);
        return "user/users";
    }

}


/*@Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyService companyService;

    @GetMapping
    public String findAll(Model model, UserFilter filter, Pageable pageable){
        Page<UserReadDto> page =  userService.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "user/users";
    }
    @GetMapping
    public String findAll(Model model, UserFilter filter, Pageable pageable){
        if(filter.firstname() != null) model.addAttribute("users", userService.findAll(filter, pageable));
        else model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}")
    public String findById(
            Model model,
            @PathVariable("id") Long id,
            @CurrentSecurityContext SecurityContext securityContext,
            @AuthenticationPrincipal UserDetails userDetails){
        UserReadDto user = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());

        return "user/user";
    }
    @PostMapping
    public String save(@ModelAttribute @Validated UserCreateDto userDto,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("user", userDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        userService.save(userDto);
        var user = userRepository.findByUsername(userDto.getUsername());
        return "redirect:/users/" + user.get().getId();
    }
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @ModelAttribute @Validated UserCreateDto userReadDto){
        System.out.println(userReadDto);
        userService.update(id, userReadDto);
        return "redirect:/users/{id}";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserReadDto userReadDto){
        model.addAttribute("user", userReadDto);
        System.out.println(model.getAttribute("user"));
        System.out.println(userReadDto);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());
        return "user/registration";
    }*/