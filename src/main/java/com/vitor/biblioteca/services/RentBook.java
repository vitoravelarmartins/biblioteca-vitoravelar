//package com.vitor.biblioteca.services;
//
//import com.vitor.biblioteca.exception.CanNotDo;
//import com.vitor.biblioteca.models.BookModel;
//import com.vitor.biblioteca.models.UserModel;
//import com.vitor.biblioteca.models.repository.BookRepository;
//import com.vitor.biblioteca.models.repository.UserRepository;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Optional;
//
//public class RentBook {
//
//    String leased = "LOCADO";
//    String withinTime = "DENTRO DO PRAZO";
//    CalcDate calcDate = new CalcDate();
//    ChecksListBooks checksListBooks = new ChecksListBooks();
//
//
//    public ResponseEntity<BookModel> toolRentBook(
//            Integer idUser,
//            Integer idBook,
//            BookModel bookDetails,
//            UserRepository userRepository,
//            BookRepository bookRepository) {
//
//
//        Optional<UserModel> inUser = userRepository.findById(idUser);
//        Optional<BookModel> inBook = bookRepository.findById(idBook);
//
//        String getUserStatus = inUser.get().getStatusUser();
//        String getBookStatus = inBook.get().getStatusBook();
//
//        if (getUserStatus != null
//                | getBookStatus.equals(leased)
//                | bookDetails.getDateRent() == null
//                | bookDetails.getDateDelivery() == null) {
//
//            verifyStatusBooks(bookRepository);
//
//          throw new CanNotDo("Livro locado ou com dataRent e dataDelivery igual a NULL");
//
//        } else if (calcDate.toolCalcDate(bookDetails.getDateRent()) >= 0
//                & calcDate.toolCalcDate(bookDetails.getDateDelivery()) >= 0) {
//
//            inBook.get().setIdUser(inUser.get());
//            //inBook.get().setDateRent(bookDetails.getDateRent());
//          //  inBook.get().setDateDelivery(bookDetails.getDateDelivery());
//            inBook.get().setStatusBook(leased);
//            inUser.get().setStatusUser(withinTime);
//            bookRepository.save(inBook.get());
//
//            verifyStatusBooks(bookRepository);
//
//
//            return ResponseEntity.accepted().body(inBook.get());
//
//
//        } else
//            throw new CanNotDo("Data invalida, ou menor que data atual");
//
//
//
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
//
//}
