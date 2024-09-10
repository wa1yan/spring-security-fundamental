package dev.waiyanhtet.spring_security_lesson2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}
