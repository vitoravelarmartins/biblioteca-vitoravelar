package com.vitor.biblioteca.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idBook;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    private String statusBook;

    }


    //  public Integer getIdBook() {
 //       return idBook;
  //  }

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getDateRent() {
//        return dateRent;
//    }
//
//    public void setDateRent(String dateRent) {
//        this.dateRent = dateRent;
//    }
//
//    public String getDateDelivery() {
//        return dateDelivery;
//    }
//
//    public void setDateDelivery(String dateDelivery) {
//        this.dateDelivery = dateDelivery;
//    }
//
//    public String getStatusBook() {
//        return statusBook;
//    }
//
//    public void setStatusBook(String statusBook) {
//        this.statusBook = statusBook;
//    }
//
//    public UserModel getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser(UserModel idUser) {
//        this.idUser = idUser;
//    }

