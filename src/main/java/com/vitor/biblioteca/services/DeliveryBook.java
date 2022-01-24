//package com.vitor.biblioteca.services;
//
//import com.vitor.biblioteca.exception.CanNotDo;
//import com.vitor.biblioteca.models.BookModel;
//import com.vitor.biblioteca.models.UserModel;
//import com.vitor.biblioteca.models.repository.BookRepository;
//import com.vitor.biblioteca.models.repository.UserRepository;
//import org.springframework.http.ResponseEntity;
//
//
//import java.util.Optional;
//
//public class DeliveryBook {
//
//    String readyToUse = "DISPONIVEL";
//
//
//    public ResponseEntity<BookModel> toolBookDelivery(Integer idUser,
//                                                      Integer idBook,
//                                                      UserRepository userRepository,
//                                                      BookRepository bookRepository) throws Exception {
//
//        Optional<UserModel> inUser = userRepository.findById(idUser);
//        Optional<BookModel> inBook = bookRepository.findById(idBook);
//
//        String userId = idUser.toString();
//
//        if (inBook.get().getStatusBook().equals(readyToUse)) {
//
//            verifyStatusBooks(bookRepository);
//            throw new CanNotDo("Não pode devolver livro, ja disponivel");
//
//        } else if (userId.equals(inBook.get().getIdUser().getId().toString())) {
//
//            inBook.get().setIdUser(null);
//          //  inBook.get().setDateRent(null);
//         //   inBook.get().setDateDelivery(null);
//            inBook.get().setStatusBook(readyToUse);
//            inUser.get().setStatusUser(null);
//            bookRepository.save(inBook.get());
//
//            verifyStatusBooks(bookRepository);
//
//            return ResponseEntity.accepted().body(inBook.get());
//        } else {
//            throw new CanNotDo("Usuario invalido para devolver o livro");
//
//        }
//    }
//
//    private void verifyStatusBooks(BookRepository bookRepository) {
//        ChecksListBooks checksListBooks = new ChecksListBooks();
//        try {
//            checksListBooks.toolChecksListBooks(bookRepository);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
