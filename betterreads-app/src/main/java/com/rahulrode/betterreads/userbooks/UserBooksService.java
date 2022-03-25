package com.rahulrode.betterreads.userbooks;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.rahulrode.betterreads.book.BookDao;
import com.rahulrode.betterreads.user.BooksByUser;
import com.rahulrode.betterreads.user.BooksByUserService;

@RequestScoped
public class UserBooksService {

  @Inject
  UserBooksDao userBooksDao;

  @Inject
  BookDao bookDao;

  @Inject
  BooksByUserService booksByUserService;

  public UserBooks findByUserIdBookId(String userId, String bookId) {
    return userBooksDao.findById(userId, bookId);
  }

  /**
   * Saves the userBook to book_by_userId_book_id
   * creates new BooksByUser object and saved it to books_by_user table
   * 
   * @param userBooks
   * @param userId
   * @return
   */
  public void addBook(UserBooksDTO userBooks, String userId) {
    var ub = UserBooksDTO.convertToUserBooks(userBooks, userId);
    userBooksDao.save(ub);

    var book = bookDao.findById(userBooks.getBookId());
    // check if the user has already rated the book
    BooksByUser booksByUser = booksByUserService.findByIdBookId(userId, book.getId());
    boolean newRecord = true;
    if (booksByUser == null)
      booksByUser = new BooksByUser();
    else
      newRecord = false;

    booksByUser.setId(userId);
    booksByUser.setBookId(book.getId());
    booksByUser.setBookName(book.getName());
    booksByUser.setCoverIds(book.getCoverids());
    booksByUser.setAuthorNames(book.getAuthorNames());
    booksByUser.setReadingStatus(userBooks.getReadingStatus());
    booksByUser.setRating(userBooks.getRating());

    if (newRecord)
      booksByUserService.save(booksByUser);
    else
      booksByUserService.update(booksByUser);
  }

}
