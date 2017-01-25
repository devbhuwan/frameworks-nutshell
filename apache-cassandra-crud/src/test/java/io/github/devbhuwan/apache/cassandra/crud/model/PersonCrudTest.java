package io.github.devbhuwan.apache.cassandra.crud.model;

import io.github.devbhuwan.apache.cassandra.crud.ApacheCassandraCrudApplication;
import io.github.devbhuwan.apache.cassandra.crud.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/19/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApacheCassandraCrudApplication.class})
public class PersonCrudTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonCrudTest.class);

    final Random random = new Random();

    @Autowired
    CassandraOperations operations;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void insertNewPerson() {
        long prevCount = personRepository.count();
        operations.insert(buildPerson());
        long nowCount = personRepository.count();
        assertEquals(nowCount, ++prevCount);
    }

    public Person buildPerson() {
        int seq = random.nextInt(50000);
        LOGGER.info("SEQ=" + seq);
        return new Person(seq, "Name-" + seq,
                "Gender-" + seq , "Address-" + seq);
    }
}