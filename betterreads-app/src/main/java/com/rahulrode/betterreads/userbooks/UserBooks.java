package com.rahulrode.betterreads.userbooks;

import java.time.LocalDate;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

/**
 * CREATE TABLE IF NOT EXISTS main.book_by_user_and_bookid (
 * user_id text,
 * book_id text,
 * reading_status text,
 * started_date date,
 * completed_date date,
 * rating int,
 * PRIMARY KEY((user_id, book_id)));
 * )
 */

@Entity
@CqlName("book_by_user_and_bookid")
public class UserBooks {

  @PartitionKey(1)
  @CqlName("user_id")
  private String userId;

  @PartitionKey(2)
  @CqlName("book_id")
  private String bookId;
  // @PartitionKey
  // private UserBooksPrimaryKey key;

  @CqlName("reading_status")
  private String readingStatus;

  @CqlName("started_date")
  private LocalDate startedDate;

  @CqlName("completed_date")
  private LocalDate completedDate;

  @CqlName("rating")
  private int rating;

  // public UserBooksPrimaryKey getKey() {
  // return this.key;
  // }

  // public void setKey(UserBooksPrimaryKey key) {
  // this.key = key;
  // }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

}
