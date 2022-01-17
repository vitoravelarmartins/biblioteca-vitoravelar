package com.vitor.biblioteca.services;

import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalcDate {
    public Integer calcDate(@PathVariable("dateSend") String dateSend) {

        Date dateToday = new Date(System.currentTimeMillis());
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date dateConvert = null;
        try {
            dateConvert = formatterDate.parse(dateSend);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateConvert.compareTo(dateToday); // -1 = Atrasado, 0 = Entrega Hoje,  1 = Dentro do prazo
    }
}
