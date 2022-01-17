package com.vitor.biblioteca.models;


import javax.persistence.*;


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
    private String statusUser;
    @ManyToOne
    @JoinColumn(name = "id_book")
    private BookModel idBook;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }

    public BookModel getIdBook() {
        return idBook;
    }

    public void setIdBook(BookModel idBook) {
        this.idBook = idBook;
    }
// UserModel user = new UserModel();

}
