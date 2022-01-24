package com.vitor.biblioteca.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class bookModelHasUserModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idBookHasUser;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel idUser;
    @ManyToOne
    @JoinColumn(name="id_book")
    private BookModel idBook;

    @Column
    public Date dateRent;
    @Column
    private Date dateDelivery;
    @Column
    private String rentStatus;

}
