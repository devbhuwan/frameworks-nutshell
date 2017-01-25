package io.github.devbhuwan.apache.cassandra.crud.model;

import lombok.*;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/19/2017
 */
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @PrimaryKey
    private Integer id;
    private String name;
    private String gender;
    private String address;

}
