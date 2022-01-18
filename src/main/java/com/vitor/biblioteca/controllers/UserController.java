package com.vitor.biblioteca.controllers;

import com.vitor.biblioteca.models.BookModel;
import com.vitor.biblioteca.models.UserModel;
import com.vitor.biblioteca.models.repository.BookRepository;
import com.vitor.biblioteca.models.repository.UserRepository;
import com.vitor.biblioteca.services.CalcDate;
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

    String readyToUse = "DISPONIVEL";
    String leased = "LOCADO";
    String delayed = "ATRASADO";
    String deliveryToday = "ENTREGA HOJE";
    String withinTime = "DENTRO DO PRAZO";


    //POST - Criar Usuarios
    @PostMapping("/create")
    public UserModel userAdd(@RequestBody UserModel user) {

        return this.userRepository.save(user);
    }

    //GET - Traz uma lista de todos os ususarios
    @GetMapping("/list")
    public List<UserModel> list() {
        BookController bookController = null;
        try {
            bookController = new BookController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            bookController.listBook();
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

    //PUT - Função de locação de livro
    @PutMapping("{idUser}/books/{idBook}/rent")
    public ResponseEntity<BookModel> bookRent(@PathVariable("idUser") Integer idUser,
                                              @PathVariable("idBook") Integer idBook,
                                              @RequestBody BookModel bookDetails) throws Exception {

        CalcDate calcDate = new CalcDate();

        Optional<UserModel> inUser = userRepository.findById(idUser);
        Optional<BookModel> inBook = bookRepository.findById(idBook);

        String getUserStatus = inUser.get().getStatusUser();
        String getBookStatus = inBook.get().getStatusBook();

        if (getUserStatus != null
                | getBookStatus.equals(leased)
                | bookDetails.getDateRent() == null
                | bookDetails.getDateDelivery() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else if (calcDate.toolCalcDate(bookDetails.getDateRent()) >= 0
                & calcDate.toolCalcDate(bookDetails.getDateRent()) >= 0) {
            inBook.get().setIdUser(inUser.get());
            inBook.get().setDateRent(bookDetails.getDateRent());
            inBook.get().setDateDelivery(bookDetails.getDateDelivery());
            inBook.get().setStatusBook(leased);
            inUser.get().setStatusUser(withinTime);
            bookRepository.save(inBook.get());
            return ResponseEntity.accepted().body(inBook.get());


        } else
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);


    }

    //PUT - Função de devolver livro
    @PutMapping("{idUser}/books/{idBook}/delivery")
    public ResponseEntity<BookModel> bookDelivery(@PathVariable("idUser") Integer idUser,
                                                  @PathVariable("idBook") Integer idBook) throws Exception {

        Optional<UserModel> inUser = userRepository.findById(idUser);
        Optional<BookModel> inBook = bookRepository.findById(idBook);

        String userId = idUser.toString();

        if(inBook.get().getStatusBook().equals(readyToUse)){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }else if(userId.equals(inBook.get().getIdUser().getId().toString())){
            inBook.get().setIdUser(null);
            inBook.get().setDateRent(null);
            inBook.get().setDateDelivery(null);
            inBook.get().setStatusBook(readyToUse);
            inUser.get().setStatusUser(null);
            bookRepository.save(inBook.get());
            return ResponseEntity.accepted().body(inBook.get());
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
