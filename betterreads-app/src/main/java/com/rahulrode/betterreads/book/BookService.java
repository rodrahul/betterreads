package com.rahulrode.betterreads.book;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.rahulrode.betterreads.search.SearchResultBook;
import com.rahulrode.betterreads.search.SearchService;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@RequestScoped
public class BookService {
  @Inject
  @RestClient
  SearchService serachSvc;

  @Inject
  BookDao bookDao;

  public List<Book> getAll() {
    return bookDao.findAll().all();
  }

  public Book findById(String bookId) {
    // If we've the book in our datastax repo we retrun it
    Book book = bookDao.findById(bookId);

    // if we don't have the book in our db, fetch from api and save it to our db
    if (book == null) {
      var result = serachSvc.search(bookId);
      var books = result.getDocs().stream().limit(1)
          .map(SearchResultBook::convertToBook)
          .collect(Collectors.toList());
      book = !books.isEmpty() ? books.get(0) : book;

      if(book != null)
        this.create(book);
    }
    return book;
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
