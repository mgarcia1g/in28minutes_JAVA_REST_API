package com.in28minutes.rest.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService){
        this.userDaoService=userDaoService;
    }


    @GetMapping("/users")
  public List<User> retrieveAllUsers(){
      return userDaoService.findAll();
  }

  @GetMapping("/usersForeach/{id}")
  public User getUserWithForeach(@PathVariable Integer id){
        return userDaoService.findUser(id);
  }

  @GetMapping("/users/{id}")
    public User getUserWithStream(@PathVariable Integer id){
        User user = userDaoService.findUserWithStream(id);
            if(user == null){
                throw new UserNotFoundException("id" + " "+id);
            }
        return user;
  }

  @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.saveUser(user);

        //location = our current uri ..(localhost:8080/addUser)... then adding path "/{id}" .. then replacing "id" with savedUser.getId and then converting to uri
      URI location = ServletUriComponentsBuilder.fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(savedUser.getId())
              .toUri();
      return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable int id){
        userDaoService.deleteUser(id);
  }

    @DeleteMapping("/users/deleteWithStream/{id}")
    public void deleteUserWithStream(@PathVariable int id){
        userDaoService.deleteUserWithStream(id);
    }
}
