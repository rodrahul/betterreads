package com.rahulrode.betterreads.book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.rahulrode.betterreads.search.SearchResultBook;
import com.rahulrode.betterreads.search.SearchService;
import com.rahulrode.betterreads.user.BooksByUser;
import com.rahulrode.betterreads.user.BooksByUserService;
import com.rahulrode.betterreads.userbooks.UserBooks;
import com.rahulrode.betterreads.userbooks.UserBooksService;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.security.identity.SecurityIdentity;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

  private final String COVER_IMAGE_ROOT = "http://covers.openlibrary.org/b/id/";

  @Inject
  BookService bookService;
  @Inject
  UserBooksService userBookService;
  @Inject
  BooksByUserService booksByUserService;

  @Inject
  SecurityIdentity securityIdentity;

  @Inject
  @RestClient
  SearchService searchService;

  @GET
  @Path("{id}")
  public BookResponse getBook(@PathParam("id") String id) {
    var book = bookService.findById(id);

    if (securityIdentity.isAnonymous())
      return new BookResponse(book, null);

    var userId = securityIdentity.getPrincipal().getName();
    var userBookDetails = userBookService.findByUserIdBookId(userId, id);

    return new BookResponse(book, userBookDetails);
  }

  @GET
  public List<BooksByUser> getBooksForUser() {
    if (securityIdentity.isAnonymous())
      return new ArrayList<>();

    var userId = securityIdentity.getPrincipal().getName();
    var booksByUser = booksByUserService.findBooksByUser(userId);
    booksByUser = booksByUser.stream().distinct().map(book -> {
      String coverUrl = null;
      if (!book.getCoverIds().isEmpty())
        coverUrl = COVER_IMAGE_ROOT + book.getCoverIds().get(0) + "-M.jpg";

      book.setCoverUrl(coverUrl);
      return book;
    }).collect(Collectors.toList());

    return booksByUser;
  }

  @GET
  @Path("search")
  public List<Book> search(@QueryParam("q") String search) {
    if (securityIdentity.isAnonymous())
      System.out.println("BOOK SEARCH ANON USER");

    var result = searchService.search(search);
    var books = result.getDocs().stream()
        .map(SearchResultBook::convertToBook)
        .collect(Collectors.toList());
    return books;
  }

}

class BookResponse {
  private Book book;
  private UserBooks userBooks;

  public BookResponse(Book book, UserBooks userBooks) {
    this.book = book;
    this.userBooks = userBooks;
  }

  public Book getBook() {
    return this.book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public UserBooks getUserBooks() {
    return this.userBooks;
  }

  public void setUserBooks(UserBooks userBooks) {
    this.userBooks = userBooks;
  }

}