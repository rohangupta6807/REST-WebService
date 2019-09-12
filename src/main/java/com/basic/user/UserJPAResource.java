package com.basic.user;

import com.basic.user.Repository.PostRepository;
import com.basic.user.Repository.UserRepository;
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
public class UserJPAResource {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostRepository postRepository;

  @GetMapping(path = "/JPA/users")
  public List<User> retrieveAllUser() {
    return userRepository.findAll();
  }

  @GetMapping(path = "/JPA/users/{id}")
  public Resource<User> retrieveUser(@PathVariable int id) {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
      throw new UserNotFoundException("id: " + id);
    }

    Resource<User> resource = new Resource(user);
    ControllerLinkBuilder controllerLinkBuilder =
        ControllerLinkBuilder.linkTo(ControllerLinkBuilder
                                         .methodOn(this.getClass())
                                         .retrieveAllUser());
    resource.add(controllerLinkBuilder.withRel("all-user"));
    return resource;
  }

  @PostMapping(path = "/JPA/users")
  public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
    User saveuser = userRepository.save(user);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(saveuser.getId())
        .toUri();
    return ResponseEntity.created(location).build();
  }

  @DeleteMapping(path = "/JPA/users/{id}")
  public void deleteUser(@PathVariable int id) {
    userRepository.deleteById(id);
  }


  @GetMapping(path = "/JPA/users/{id}/posts")
  public List<Post> retrieveAllUsersPost(@PathVariable int id) {
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("id: " + id);
    }
    return userOptional.get().getPost();
  }


  @PostMapping(path = "/JPA/users/{id}/posts")
  public ResponseEntity<?> createPost(@PathVariable int id, @RequestBody Post post) {
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("id: " + id);
    }

    User user = userOptional.get();
    post.setUser(user);
    postRepository.save(post);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(post.getId())
        .toUri();
    return ResponseEntity.created(location).build();
  }

}

