package com.vitor.biblioteca.services;

import com.vitor.biblioteca.models.BookModel;
import com.vitor.biblioteca.models.repository.BookRepository;


import java.util.Optional;

public class CalcDateInBook {
    String readyToUse = "DISPONIVEL";

    public Integer toolCalcDateInBook(Integer idBook, BookRepository bookRepository) {

        CalcDate calcDate = new CalcDate();
        Optional<BookModel> inBook = bookRepository.findById(idBook);

        if (inBook.get().getStatusBook().equals(readyToUse)) {
            return 2; //Livros que não foram LOCADOS vão retonar "2"
        } else
            return calcDate.toolCalcDate(inBook.get().getDateDelivery());
    }
}
