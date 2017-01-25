package io.github.devbhuwan.apache.cassandra.crud;

import io.github.devbhuwan.apache.cassandra.crud.model.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/19/2017
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {

}
