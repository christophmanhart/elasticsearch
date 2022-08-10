package com.christoph.elasticsearch.repository;

import com.christoph.elasticsearch.document.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person, String> {
//Extends -> das erste ist das Dokument und das zweite der Datentyp der Id

}
