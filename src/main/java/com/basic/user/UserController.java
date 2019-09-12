package com.basic.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

  @Autowired
  private UserDaoService userDaoService;

  @GetMapping(path = "/users")
  public List<User> retrieveAllUser() {
    return userDaoService.findAll();
  }


  @GetMapping(path = "/users/{id}")
  public Resource<User> retrieveUser(@PathVariable int id) {
    Optional<User> user = userDaoService.findOne(id);
    if (!user.isPresent()) {
      throw new UserNotFoundException("id: " + id);
    }

    Resource<User> resource = new Resource(user);
    ControllerLinkBuilder controllerLinkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder
                                                                                   .methodOn(this.getClass())
                                                                                   .retrieveAllUser());
    resource.add(controllerLinkBuilder.withRel("all-user"));
    return resource;
  }

  @PostMapping(path = "/users")
  public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
    User insertedUser = userDaoService.save(user);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(insertedUser.getId())
        .toUri();
    return ResponseEntity.created(location).build();
  }

  @DeleteMapping(path = "/users/{id}")
  public void deleteUser(@PathVariable int id) {
    User user = userDaoService.deleteById(id);
    if (null == user) {
      throw new UserNotFoundException("id: " + id);
    }
  }

}
