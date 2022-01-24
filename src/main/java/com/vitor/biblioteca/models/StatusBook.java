package com.vitor.biblioteca.models;

public enum StatusBook {
    INDISPONIVEL("INDISPONÍVEL"),
    DISPONIVEL("DISPONÍVEL");

    private  String status;

    StatusBook(String status) {
        this.status=status;
    }
}
