//package com.vitor.biblioteca.services;
//
//import com.vitor.biblioteca.models.BookModel;
//import com.vitor.biblioteca.models.repository.BookRepository;
//
//import java.util.List;
//
//public class ChecksListBooks {
//
//    String leased = "LOCADO";
//    String delayed = "ATRASADO";
//    String deliveryToday = "ENTREGA HOJE";
//    String withinTime = "DENTRO DO PRAZO";
//
//
//    public List<BookModel> toolChecksListBooks(BookRepository bookRepository) throws Exception {
//        CalcDateInBook calcDateInBook = new CalcDateInBook();
//        SettingStatusUser settingStatusUser = new SettingStatusUser();
//
//        List<BookModel> listBook = bookRepository.findAll();
//
//        for (BookModel bookModel : listBook) {
//            if (bookModel.getStatusBook().equals(leased)) {
//                System.out.println(bookModel.getTitle());
//
//                Integer idBook = bookModel.getIdBook();
//                Integer returnCalcDate = calcDateInBook.toolCalcDateInBook(idBook,bookRepository);
//
//                switch (returnCalcDate) {
//                    case -1:
//
//                        settingStatusUser.toolSettingStatusUser(idBook, delayed, bookRepository);
//                        System.out.println("-1 "+delayed);
//                        break;
//                    case 0:
//                        settingStatusUser.toolSettingStatusUser(idBook, deliveryToday, bookRepository);
//                        System.out.println("0 "+deliveryToday);
//                        break;
//                    case 1:
//                        settingStatusUser.toolSettingStatusUser(idBook, withinTime, bookRepository);
//                        System.out.println("1 "+withinTime);
//                        break;
//                }
//            }
//        }
//        return bookRepository.findAll();
//    }
//}
