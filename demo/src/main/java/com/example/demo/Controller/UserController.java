package com.example.demo.Controller;

import com.example.demo.jpa.UserRepo;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepo repo;
    @GetMapping("/list")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(repo.findAll());
    }
     @GetMapping("/getUser/{id}")
     public ResponseEntity<User> getUser(@PathVariable int id){
        return  ResponseEntity.ok(repo.findById(id));
     }
    @PostMapping("/insert")
    public ResponseEntity<User> insert(@RequestBody User u){
        return ResponseEntity.ok(repo.save(u));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user){
        User findUser = repo.findById(id);
        findUser.setName(user.getName());
        return ResponseEntity.ok(repo.save(findUser));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        repo.delete(repo.findById(id));
        return new ResponseEntity(HttpStatus.OK);
    }
}
