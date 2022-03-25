package com.rahulrode.betterreads.userbooks;

import java.time.LocalDate;

public class UserBooksDTO {

  private String bookId;

  private String readingStatus;

  private LocalDate startedDate;

  private LocalDate completedDate;

  private int rating;

  public UserBooksDTO() {
  }

  public UserBooksDTO(String bookId, String readingStatus, LocalDate startedDate, LocalDate completedDate, int rating) {
    this.bookId = bookId;
    this.readingStatus = readingStatus;
    this.startedDate = startedDate;
    this.completedDate = completedDate;
    this.rating = rating;
  }

  public static UserBooks convertToUserBooks(UserBooksDTO userBooks, String userId) {
    // var key = new UserBooksPrimaryKey(userId, userBooks.getBookId());
    var ub = new UserBooks();
    // ub.setKey(key);
    ub.setUserId(userId);
    ub.setBookId(userBooks.getBookId());
    ub.setStartedDate(userBooks.getStartedDate());
    ub.setCompletedDate(userBooks.getCompletedDate());
    ub.setReadingStatus(userBooks.getReadingStatus());
    ub.setRating(userBooks.getRating());

    return ub;
  }

  public String getBookId() {
    return this.bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public String getReadingStatus() {
    return this.readingStatus;
  }

  public void setReadingStatus(String readingStatus) {
    this.readingStatus = readingStatus;
  }

  public LocalDate getStartedDate() {
    return this.startedDate;
  }

  public void setStartedDate(LocalDate startedDate) {
    this.startedDate = startedDate;
  }

  public LocalDate getCompletedDate() {
    return this.completedDate;
  }

  public void setCompletedDate(LocalDate completedDate) {
    this.completedDate = completedDate;
  }

  public int getRating() {
    return this.rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    return "{" +
        " bookId='" + getBookId() + "'" +
        ", readingStatus='" + getReadingStatus() + "'" +
        ", startedDate='" + getStartedDate() + "'" +
        ", completedDate='" + getCompletedDate() + "'" +
        ", rating='" + getRating() + "'" +
        "}";
  }

}