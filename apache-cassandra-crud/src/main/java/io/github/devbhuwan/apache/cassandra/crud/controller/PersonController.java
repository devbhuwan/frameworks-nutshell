package io.github.devbhuwan.apache.cassandra.crud.controller;

import io.github.devbhuwan.apache.cassandra.crud.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/19/2017
 */
@Controller
@RequestMapping("/person")
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private CassandraOperations operations;
    Random random = new Random();

    @RequestMapping("/add")
    public String add(Model model) {
        int seq = random.nextInt(50000);
        LOGGER.info("SEQ=" + seq);
        operations.insert(new Person(seq, "Name-" + seq,
                "Gender-" + seq , "Address-" + seq));
        return "redirect:/";
    }

}
