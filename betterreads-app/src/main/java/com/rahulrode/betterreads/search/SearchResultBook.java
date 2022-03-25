package com.rahulrode.betterreads.search;

import java.time.LocalDate;
import java.util.List;

import com.rahulrode.betterreads.book.Book;

public class SearchResultBook {

  private String key;
  private String title;
  private List<String> author_key;
  private List<String> author_name;
  private String cover_i;
  private int first_publish_year;

  public String getKey() {
    return this.key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getAuthor_key() {
    return this.author_key;
  }

  public void setAuthor_key(List<String> author_key) {
    this.author_key = author_key;
  }

  public List<String> getAuthor_name() {
    return this.author_name;
  }

  public void setAuthor_name(List<String> author_name) {
    this.author_name = author_name;
  }

  public String getCover_i() {
    return this.cover_i;
  }

  public void setCover_i(String cover_i) {
    this.cover_i = cover_i;
  }

  public int getFirst_publish_year() {
    return this.first_publish_year;
  }

  public void setFirst_publish_year(int first_publish_year) {
    this.first_publish_year = first_publish_year;
  }

  public static Book convertToBook(SearchResultBook srBook) {
    Book book = new Book();
    book.setId(srBook.getKey().replace("/works/", ""));
    book.setName(srBook.getTitle());
    LocalDate date = LocalDate.of(srBook.getFirst_publish_year(), 1, 1);
    book.setPublishedDate(date);
    var coverId = srBook.getCover_i();
    if (coverId != null)
      book.setCoverids(List.of(coverId));
    book.setAuthorIds(srBook.getAuthor_key());
    book.setAuthorNames(srBook.getAuthor_name());

    return book;
  }
}
