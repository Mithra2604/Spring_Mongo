package com.example.Spring_Mongo.Controller;

import com.example.Spring_Mongo.Model.UserDetails;
import com.example.Spring_Mongo.repository.UsrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UsrRepository user;

    @PostMapping("/register")
    public String register(@RequestBody UserDetails userdetails){
        boolean exist=false;
        for(UserDetails x:user.findAll()){
            if(userdetails.getEmail().equals(x.getEmail())){
                exist=true;
            }
        }
        if(exist==false){
            user.save(userdetails);
            return"email added";
        }
        else{
            return "email already exists";
        }
    }
    @GetMapping("/getallregister")
    public List<UserDetails> getallregister(){
        return user.findAll();
    }

    @PutMapping("/update/{id}")
    public String updateregister(@PathVariable String id, @RequestBody UserDetails userdetails)
    {
        Optional<UserDetails> existingUsername= user.findById(id);
        if(existingUsername.isEmpty()){
            return "No Movies Found";
        }
        else {
            existingUsername.get().setUsername((userdetails.getUsername()));
            existingUsername.get().setEmail((userdetails.getEmail()));
            existingUsername.get().setNumber((userdetails.getNumber()));
            existingUsername.get().setPassword((userdetails.getPassword()));
            user.save(existingUsername.get());
            return "Username Updated";
        }
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        user.deleteById(id);
        return "deleted";
    }
}
