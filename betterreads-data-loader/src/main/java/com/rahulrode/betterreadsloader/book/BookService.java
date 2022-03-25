package com.rahulrode.betterreadsloader.book;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class BookService {
  @Inject
  BookDao bookDao;

  public List<Book> getAll() {
    return bookDao.findAll().all();
  }

  public Book findById(String bookId) {
    return bookDao.findById(bookId);
  }

  public void create(Book book) {
    bookDao.save(book);
  }

  public void update(Book book) {
    bookDao.update(book);
  }

  public void delete(String bookId) {
    bookDao.deleteById(bookId);
  }

  public void delete(Book book) {
    bookDao.delete(book);
  }
  
}
