package com.vitor.biblioteca.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    private String password;
    private String statusUser;


//    public Integer getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getStatusUser() {
//        return statusUser;
//    }
//
//    public void setStatusUser(String statusUser) {
//        this.statusUser = statusUser;
//    }

}
