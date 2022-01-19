package com.vitor.biblioteca.controllers;

import com.vitor.biblioteca.exception.CanNotDo;
import com.vitor.biblioteca.models.BookModel;
import com.vitor.biblioteca.models.repository.BookRepository;
import com.vitor.biblioteca.services.ChecksListBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books") // http://localhost:8080/books
public class BookController {

    @Autowired
    private BookRepository bookRepository;


    String readyToUse = "DISPONIVEL";
    String leased = "LOCADO";


    //POST - Criar Livro
    @PostMapping("/create")
    public BookModel bookCreate(@RequestBody BookModel book) {
        book.setStatusBook(readyToUse);
       verifyStatusBooks(bookRepository);
        return this.bookRepository.save(book);
    }

    //GET - Procura livro pelo NOME
    @GetMapping("/title/{title}")
    public List<BookModel> findTitle(@PathVariable("title") String title) {
        verifyStatusBooks(bookRepository);
        return this.bookRepository.findByTitleIgnoreCase(title);
    }

    //PUT - Editar livros
    @PutMapping("/{idBook}/edit")
    public ResponseEntity<BookModel> bookEdit(@PathVariable("idBook") Integer idBook, @RequestBody BookModel bookDetails) throws Exception {

        Optional<BookModel> inBook = bookRepository.findById(idBook);
        inBook.get().setAuthor(bookDetails.getAuthor());
        inBook.get().setTitle(bookDetails.getTitle());
        bookRepository.save(inBook.get());
        verifyStatusBooks(bookRepository);
        return ResponseEntity.accepted().body(inBook.get());
    }


    @DeleteMapping("/{idBook}/delete")
    public ResponseEntity<BookModel> deleteBook(@PathVariable("idBook") Integer idBook){
        Optional<BookModel> inBook = bookRepository.findById(idBook);
        if(inBook.get().getStatusBook().equals(leased)){
            throw new CanNotDo("Não pode deletar livro locado!");
        }else
            verifyStatusBooks(bookRepository);
            bookRepository.delete(inBook.get());
        return  new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/list")
    public List<BookModel> listBook() throws Exception {
        ChecksListBooks checksListBooks =new ChecksListBooks();
        checksListBooks.toolChecksListBooks(bookRepository);
        return this.bookRepository.findAll();

    }

    private void verifyStatusBooks(BookRepository bookRepository) {
        ChecksListBooks checksListBooks = new ChecksListBooks();
        try {
            checksListBooks.toolChecksListBooks(bookRepository);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
