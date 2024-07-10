/*
package spring.ws.carrentaldirectoryweb.http.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.dataBase.repository.UserRepository;
import spring.dto.PageResponse;
import spring.dto.UserCreateDto;
import spring.dto.UserFilter;
import spring.dto.UserReadDto;
import spring.service.CompanyService;
import spring.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyService companyService;

    @GetMapping
    public PageResponse<UserReadDto> findAll(UserFilter filter, Pageable pageable){
        Page<UserReadDto> page =  userService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Long id){
        return userService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@Validated @RequestBody UserCreateDto userDto){
        UserReadDto user = userService.save(userDto);
        return "redirect:/users/" + user.getId();
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Integer id, @Validated  @RequestBody UserCreateDto userReadDto){
        return userService.update(id, userReadDto);
    }
    @DeleteMapping ("/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }


    @GetMapping(value = "/{id}/avatar", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> findAvatar(@PathVariable("id") Long id) {
        return userService.findAvatar(id)
                .map(content -> ResponseEntity.status(HttpStatus.CREATED) // Устанавливаем статус 201
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(content.length)) // Устанавливаем правильный Content-Length
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"avatar_" + id + ".png\"")
                        .body(content)
                )
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
*/
