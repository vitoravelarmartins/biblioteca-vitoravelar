package com.vitor.biblioteca.services;

import com.vitor.biblioteca.models.BookModel;
import com.vitor.biblioteca.models.UserModel;
import com.vitor.biblioteca.models.repository.BookRepository;
import com.vitor.biblioteca.models.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

public class DeliveryBook {

    String readyToUse = "DISPONIVEL";
    ChecksListBooks checksListBooks =new ChecksListBooks();

    public ResponseEntity<BookModel> toolBookDelivery(Integer idUser,
                                                      Integer idBook,
                                                      UserRepository userRepository,
                                                      BookRepository bookRepository) throws Exception {

        Optional<UserModel> inUser = userRepository.findById(idUser);
        Optional<BookModel> inBook = bookRepository.findById(idBook);

        String userId = idUser.toString();

        if(inBook.get().getStatusBook().equals(readyToUse)){

            try {
                checksListBooks.toolChecksListBooks(bookRepository);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }else if(userId.equals(inBook.get().getIdUser().getId().toString())){

            inBook.get().setIdUser(null);
            inBook.get().setDateRent(null);
            inBook.get().setDateDelivery(null);
            inBook.get().setStatusBook(readyToUse);
            inUser.get().setStatusUser(null);
            bookRepository.save(inBook.get());

            try {
                checksListBooks.toolChecksListBooks(bookRepository);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return ResponseEntity.accepted().body(inBook.get());
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
