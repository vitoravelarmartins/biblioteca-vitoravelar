package com.vitor.biblioteca.controllers;

import com.vitor.biblioteca.models.BookModel;
import com.vitor.biblioteca.models.UserModel;
import com.vitor.biblioteca.models.repository.BookRepository;
import com.vitor.biblioteca.models.repository.UserRepository;
import com.vitor.biblioteca.services.CalcDate;
import com.vitor.biblioteca.services.ChecksListBooks;
import com.vitor.biblioteca.services.DeliveryBook;
import com.vitor.biblioteca.services.RentBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    ChecksListBooks checksListBooks =new ChecksListBooks();

    //POST - Criar Usuarios
    @PostMapping("/create")
    public UserModel userAdd(@RequestBody UserModel user) {
        try {
            checksListBooks.toolChecksListBooks(bookRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.userRepository.save(user);
    }

    //GET - Traz uma lista de todos os ususarios
    @GetMapping("/list")
    public List<UserModel> list() {
        try {
            checksListBooks.toolChecksListBooks(bookRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.userRepository.findAll();
    }

    // GET - Traz usario do ID especifico
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> userScan(@PathVariable("id") Integer id) {
        Optional<UserModel> userFind = this.userRepository.findById(id);

        if (userFind.isPresent()) {
            return ResponseEntity.accepted().body(userFind.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //GET - Procura usuario pelo NOME
    @GetMapping("/name/{name}")
    public List<UserModel> findName(@PathVariable("name") String name) {
        return this.userRepository.findByNameIgnoreCase(name);
    }

    @PutMapping("{idUser}/books/{idBook}/rent")
    public ResponseEntity bookRent(@PathVariable("idUser") Integer idUser,
                                              @PathVariable("idBook") Integer idBook,
                                              @RequestBody BookModel bookDetails) throws Exception {
        RentBook rentBook = new RentBook();
        return rentBook.toolRentBook(idUser,idBook,bookDetails,userRepository,bookRepository);

    }
    @PutMapping("{idUser}/books/{idBook}/delivery")
    public ResponseEntity bookDelivery(@PathVariable("idUser") Integer idUser,
                                   @PathVariable("idBook") Integer idBook,
                                   @RequestBody BookModel bookDetails) throws Exception {
        DeliveryBook deliveryBook = new DeliveryBook();
        return deliveryBook.toolBookDelivery(idUser,idBook,userRepository,bookRepository);

    }


}
