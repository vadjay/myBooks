package ru.otn486.mybooks.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otn486.mybooks.dao.BookDao;
import ru.otn486.mybooks.models.Book;
import ru.otn486.mybooks.models.SearchAttribute;

import java.util.List;

@Service
public class BookService {

    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional
    public void addBook(Book book){
        this.bookDao.addBook(book);
    }

    @Transactional
    public void updateBook(Book book){
        this.bookDao.updateBook(book);
    }

    @Transactional
    public void removeBook(int id){
        this.bookDao.removeBook(id);
    }

    @Transactional
    public Book getBookById(int id){
        return this.bookDao.getBookById(id);
    }

    @Transactional
    public List<Book> listBooks(SearchAttribute searchAttribute) {
        return this.bookDao.listBooks(searchAttribute);
    }
}
