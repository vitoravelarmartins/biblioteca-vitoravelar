package com.vitor.biblioteca.services;

import com.vitor.biblioteca.models.BookModel;
import com.vitor.biblioteca.models.UserModel;
import com.vitor.biblioteca.models.repository.BookRepository;
import com.vitor.biblioteca.models.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class RentBook {

    String leased = "LOCADO";
    String withinTime = "DENTRO DO PRAZO";
    CalcDate calcDate = new CalcDate();
    ChecksListBooks checksListBooks =new ChecksListBooks();


    public ResponseEntity<BookModel> toolRentBook(Integer idUser,
                                                  Integer idBook,
                                                  BookModel bookDetails,
                                                  UserRepository userRepository,
                                                  BookRepository bookRepository) throws Exception {


        Optional<UserModel> inUser = userRepository.findById(idUser);
        Optional<BookModel> inBook = bookRepository.findById(idBook);

        String getUserStatus = inUser.get().getStatusUser();
        String getBookStatus = inBook.get().getStatusBook();

        if (getUserStatus != null
                | getBookStatus.equals(leased)
                | bookDetails.getDateRent() == null
                | bookDetails.getDateDelivery() == null) {

            try {
                checksListBooks.toolChecksListBooks(bookRepository);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        } else if (calcDate.toolCalcDate(bookDetails.getDateRent()) >= 0
                & calcDate.toolCalcDate(bookDetails.getDateDelivery()) >= 0) {
            inBook.get().setIdUser(inUser.get());
            inBook.get().setDateRent(bookDetails.getDateRent());
            inBook.get().setDateDelivery(bookDetails.getDateDelivery());
            inBook.get().setStatusBook(leased);
            inUser.get().setStatusUser(withinTime);
            bookRepository.save(inBook.get());

            try {
                checksListBooks.toolChecksListBooks(bookRepository);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return ResponseEntity.accepted().body(inBook.get());


        } else
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);


    }

}
