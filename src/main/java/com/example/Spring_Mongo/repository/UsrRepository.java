package com.example.Spring_Mongo.repository;

import com.example.Spring_Mongo.Model.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsrRepository  extends MongoRepository<UserDetails,String>
{
    UserDetails findByusername(String name);
}
