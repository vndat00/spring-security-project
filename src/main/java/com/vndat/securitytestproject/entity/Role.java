package com.vndat.securitytestproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    public Role(String name){
        this.name = name;
    }
    public Role(Integer id){
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
