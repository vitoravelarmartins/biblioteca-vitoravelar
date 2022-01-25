package com.vitor.biblioteca.controllers;

import com.vitor.biblioteca.exception.ApiExeceptionHandler;
import com.vitor.biblioteca.exception.ThereIsNot;
import com.vitor.biblioteca.models.UserModel;
import com.vitor.biblioteca.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

    @Autowired
    private UserRepository userRepository;


    //POST - Criar Usuarios
    @PostMapping
    public UserModel userAdd(@RequestBody UserModel user) {
        return this.userRepository.save(user);
    }

    //GET - Traz uma lista de todos os ususarios
    @GetMapping
    public List<UserModel> list() {
        return this.userRepository.findAll();
    }

     //GET - Traz usario do ID especifico
    @GetMapping("/{idUser}")
    public ResponseEntity<UserModel> userScan(@PathVariable("idUser") Integer idUser) {
        Optional<UserModel> userFind = this.userRepository.findById(idUser);
        if (userFind.isPresent()) {
            return ResponseEntity.accepted().body(userFind.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value ={"/filter"}, method = RequestMethod.GET)
    public ResponseEntity<UserModel> findName(@RequestParam(value = "name") String name) {
       Optional<UserModel> lisUserModel = this.userRepository.findByNameContains(name);
        if (lisUserModel.isPresent()){
            return ResponseEntity.accepted().body(lisUserModel.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

       //PUT - Editar Userd
    @PutMapping("/{idUser}")
    public ResponseEntity<UserModel> userEdit(
            @PathVariable("idUser") Integer idUser,
            @RequestBody UserModel userDetails) throws Exception {
        Optional<UserModel> inUser = userRepository.findById(idUser);

        if (inUser.isPresent()) {
            inUser.get().setName(userDetails.getName());
            inUser.get().setEmail(userDetails.getEmail());
            userRepository.save(inUser.get());



            return ResponseEntity.accepted().body(inUser.get());
        }
        throw new ThereIsNot("Usuário não existe");
    }

//
//    @PutMapping("{idUser}/books/{idBook}/rent")
//    public ResponseEntity bookRent(@PathVariable("idUser") Integer idUser,
//                                              @PathVariable("idBook") Integer idBook,
//                                              @RequestBody BookModel bookDetails) throws Exception {
//        RentBook rentBook = new RentBook();
//        return rentBook.toolRentBook(idUser,idBook,bookDetails,userRepository,bookRepository);
//
//    }
//    @PutMapping("{idUser}/books/{idBook}/delivery")
//    public ResponseEntity bookDelivery(@PathVariable("idUser") Integer idUser,
//                                   @PathVariable("idBook") Integer idBook,
//                                   @RequestBody BookModel bookDetails) throws Exception {
//     //   DeliveryBook deliveryBook = new DeliveryBook();
//      //  return deliveryBook.toolBookDelivery(idUser,idBook,userRepository,bookRepository);
//
//    }


//    private void verifyStatusBooks(BookRepository bookRepository) {
//        ChecksListBooks checksListBooks = new ChecksListBooks();
//        try {
//            checksListBooks.toolChecksListBooks(bookRepository);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
