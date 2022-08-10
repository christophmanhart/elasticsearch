package com.christoph.elasticsearch.service;

import com.christoph.elasticsearch.document.Person;
import com.christoph.elasticsearch.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.repository = personRepository;
    }

    public void save(final Person person){
        repository.save(person);
    }

    public Person findById(final String id){
        return repository.findById(id).orElse(null);
    }
}
