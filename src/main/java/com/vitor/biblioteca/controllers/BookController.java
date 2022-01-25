package com.vitor.biblioteca.controllers;


import com.vitor.biblioteca.models.BookModel;
import com.vitor.biblioteca.models.StatusBook;
import com.vitor.biblioteca.models.repository.BookRepository;
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


    //POST - Criar Livro
    @PostMapping
    public BookModel bookCreate(@RequestBody BookModel book) {
        book.setStatusBook(String.valueOf(StatusBook.DISPONIVEL));
        return this.bookRepository.save(book);
    }

    //GET - lista de Livros
    @GetMapping
    public List<BookModel> list() {
        return this.bookRepository.findAll();
    }

    //GET - Procura livro pelo NOME
    @GetMapping("/{idBook}")
    public ResponseEntity<BookModel> findTitle(@PathVariable("idBook") Integer idBook) {
        Optional<BookModel> inBook = bookRepository.findById(idBook);
        if (inBook.isPresent()) {
            return ResponseEntity.accepted().body(inBook.get());
        }
return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
//http://localhost:8080/books/?idBook=33
    @RequestMapping(value ={"/filter"}, method = RequestMethod.GET)
    public List<BookModel> findBookId(@RequestParam(value = "title") String title) {
        return this.bookRepository.findByTitleContains(title);
    }


    //PUT - Editar livros
    @PutMapping("/{idBook}")
    public ResponseEntity<BookModel> bookEdit(@PathVariable("idBook") Long idBook,
                                              @RequestBody BookModel bookDetails) throws Exception {

        Optional<BookModel> inBook = bookRepository.findById(Math.toIntExact(idBook));
        inBook.get().setAuthor(bookDetails.getAuthor());
        inBook.get().setTitle(bookDetails.getTitle());
        inBook.get().setAmount(bookDetails.getAmount());
        bookRepository.save(inBook.get());

        return ResponseEntity.accepted().body(inBook.get());
    }

//    @DeleteMapping("/{idBook}")
//    public ResponseEntity<BookModel> deleteBook(@PathVariable("idBook") Long idBook){
//        Optional<BookModel> inBook = bookRepository.findById(Math.toIntExact(idBook));
//        if(inBook.get().getStatusBook().equals(leased)){
//            throw new CanNotDo("Não pode deletar livro locado!");
//        }else
//            bookRepository.delete(inBook.get());
//        return  new ResponseEntity<>(HttpStatus.ACCEPTED);
//    }
//
//    @GetMapping
//    public List<BookModel> listBook() throws Exception {
//        return this.bookRepository.findAll();
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
