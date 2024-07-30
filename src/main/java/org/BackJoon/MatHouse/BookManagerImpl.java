package org.BackJoon.MatHouse;

import java.util.ArrayList;

public class BookManagerImpl implements IBookManager{
    final static int MAX_SIZE = 100;
    private ArrayList<Book> books = new ArrayList<>();

    private static BookManagerImpl instance;
    private BookManagerImpl() {
    }
    public static BookManagerImpl getInstance(){
        if(instance==null)
            instance = new BookManagerImpl();
        return instance;
    }

    @Override
    public void add(Book book){
        books.add(book);
    }
    @Override
    public void remove(String isbn){
        for(int i=0;i<books.size();i++)
        {
            if(books.get(i).getIsbn().contains(isbn)){
                books.remove(i);
                break;
            }
        }
    }
//    @Override
//    public ArrayList getList(){
//        return books;
//    }
    @Override
    public Book[] getListByArray(){
        Book[] bookList = new Book[books.size()];
        return books.toArray(bookList);
    }
    @Override
    public Book searchByIsbn(String isbn){
        for(int i=0;i<books.size();i++)
        {
            if(books.get(i).getIsbn().contains(isbn)){
                return books.get(i);
            }
        }
        return null;
    }
    @Override
    public Book[] searchByTitle(String title){
        ArrayList<Book> returnBookList = new ArrayList<>();

        for(int i=0;i<books.size();i++){
            if(books.get(i).getTitle().contains(title))
                returnBookList.add(books.get(i));
        }
        Book[] books1 = new Book[returnBookList.size()];
        returnBookList.toArray(books1);
        return books1;
    }
    @Override
    public Book[] getBooks(){
        ArrayList<Book> returnBookList = new ArrayList<>();
        for(int i=0;i<books.size();i++)
        {
            if(!(books.get(i) instanceof Magazine))
                returnBookList.add(books.get(i));
        }
        Book[] returnBook = new Book[returnBookList.size()];
        returnBookList.toArray(returnBook);
        return returnBook;
    }
    @Override
    public Magazine[] getMagazines(){
        ArrayList<Magazine> returnBookList = new ArrayList<>();
        for(int i=0;i<books.size();i++)
        {
            if(books.get(i) instanceof Magazine)
                returnBookList.add((Magazine) books.get(i));
        }
        Magazine[] returnBook = new Magazine[returnBookList.size()];
        returnBookList.toArray(returnBook);
        return returnBook;
    }
    @Override
    public int getTotalPrice() {
        int totalPrice=0;
        for(int i=0; i < books.size(); i++)
            totalPrice += books.get(i).getPrice();
        return totalPrice;
    }
    @Override
    public double getPriceAvg() {
        return (double)getTotalPrice()/books.size();
    }

    @Override
    public void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException {
        Book book = searchByIsbn(isbn);
        if(book == null){
            throw new ISBNNotFoundException(isbn);
        }
        if(quantity > book.getQuantity()){
            throw new QuantityException();
        }
    }

    @Override
    public void buy(String isbn, int quantity) throws ISBNNotFoundException {
        Boolean flag=false;
        for(int i=0;i<books.size();i++)
        {
            if(books.get(i).getIsbn().equals(isbn))
                flag=true;
        }
        if(flag==false) {
            throw new ISBNNotFoundException(isbn);
        }
    }

}