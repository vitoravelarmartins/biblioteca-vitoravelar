package com.vitor.biblioteca.services;

import com.vitor.biblioteca.models.BookModel;
import com.vitor.biblioteca.models.repository.BookRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public class SettingStatusUser {

    public void toolSettingStatusUser(@PathVariable("idBook") Integer idBook, @PathVariable("statusValueUser") String statusValueUser, @PathVariable("bookRepo") BookRepository bookRepository) throws Exception {
        Optional<BookModel> inBook = bookRepository.findById(idBook);
        inBook.get().getIdUser().getStatusUser();
        inBook.get().getIdUser().setStatusUser(statusValueUser);
        bookRepository.save(inBook.get());
    }

}
