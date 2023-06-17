package com.study.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;

    @ManyToMany(targetEntity = Authorities.class,cascade = CascadeType.ALL)
    @JoinTable(name="users_authorities",
            joinColumns=@JoinColumn(name="user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="authorities_id",
            referencedColumnName = "id"))
    private Set<Authorities> authorities=new HashSet<>();

}
