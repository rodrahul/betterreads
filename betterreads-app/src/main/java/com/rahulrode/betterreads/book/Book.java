package com.rahulrode.betterreads.book;

import java.time.LocalDate;
import java.util.List;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CREATE TABLE IF NOT EXISTS main.book_by_id(
 * book_id text PRIMARY KEY,
 * book_name text,
 * book_description text,
 * published_date date,
 * cover_ids list<text>,
 * author_ids list<text>,
 * author_names list<text>
 * )
 */
@Entity
@CqlName("book_by_id")
public class Book {

  @PartitionKey
  @CqlName("book_id")
  private String id;

  @CqlName("book_name")
  private String name;

  @CqlName("book_description")
  private String description;

  @CqlName("published_date")
  private LocalDate publishedLocalDate;

  @CqlName("cover_ids")
  private List<String> coverids;

  @CqlName("author_ids")
  private List<String> authorIds;

  @CqlName("author_names")
  private List<String> authorNames;

  public Book() {
  }

  public Book(String id, String name, String description, LocalDate publishedLocalDate, List<String> coverids,
      List<String> authorIds, List<String> authorNames) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.publishedLocalDate = publishedLocalDate;
    this.coverids = coverids;
    this.authorIds = authorIds;
    this.authorNames = authorNames;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getPublishedDate() {
    return this.publishedLocalDate;
  }

  public void setPublishedDate(LocalDate publishedLocalDate) {
    this.publishedLocalDate = publishedLocalDate;
  }

  @JsonProperty("coverIds")
  public List<String> getCoverids() {
    return this.coverids;
  }

  public void setCoverids(List<String> coverIds) {
    this.coverids = coverIds;
  }

  public List<String> getAuthorIds() {
    return this.authorIds;
  }

  public void setAuthorIds(List<String> authorIds) {
    this.authorIds = authorIds;
  }

  public List<String> getAuthorNames() {
    return this.authorNames;
  }

  public void setAuthorNames(List<String> authorNames) {
    this.authorNames = authorNames;
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", name='" + getName() + "'" +
        ", description='" + getDescription() + "'" +
        ", publishedLocalDate='" + getPublishedDate() + "'" +
        ", coverids='" + getCoverids() + "'" +
        ", authorIds='" + getAuthorIds() + "'" +
        ", authorNames='" + getAuthorNames() + "'" +
        "}";
  }

}
