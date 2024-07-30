package org.BackJoon.MatHouse;

public interface IBookManager {
    public abstract void add(Book book);
    public abstract void remove(String isbn);
//    Book[] getList();
    Book[] getListByArray();
    Book searchByIsbn(String isbn);
    Book[] searchByTitle(String title);
    Magazine[] getMagazines();
    Book[] getBooks();
    int getTotalPrice();
    double getPriceAvg();
    public abstract void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException;
    public abstract void buy(String isbn, int quantity) throws ISBNNotFoundException;
}
