package com.vitor.biblioteca.services;

import com.vitor.biblioteca.models.BookModel;
import com.vitor.biblioteca.models.repository.BookRepository;

import java.util.Optional;

public class SettingStatusUser {

    public void toolSettingStatusUser(Integer idBook,
                                      String statusValueUser,
                                      BookRepository bookRepository) throws Exception {

        Optional<BookModel> inBook = bookRepository.findById(idBook);
        inBook.get().getIdUser().getStatusUser();
        inBook.get().getIdUser().setStatusUser(statusValueUser);
        bookRepository.save(inBook.get());
    }
}
