package com.rahulrode.betterreads.user;

import java.util.List;
import java.util.UUID;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.Transient;

/**
 * CREATE TABLE IF NOT EXISTS main.books_by_user (
 * user_id text,
 * book_id text,
 * time_uuid timeuuid,
 * reading_status text,
 * book_name text,
 * author_names list<text>,
 * cover_ids list<text>,
 * rating int,
 * PRIMARY KEY (user_id, book_id,reading_status, time_uuid)
 * )
 * WITH CLUSTERING ORDER BY (book_id ASC, reading_status ASC, time_uuid DESC);
 */
@Entity
@CqlName("books_by_user")
public class BooksByUser {

  @CqlName("user_id")
  @PartitionKey()
  private String id;

  @CqlName("book_id")
  @ClusteringColumn(1)
  private String bookId;

  @CqlName("time_uuid")
  @ClusteringColumn(2)
  private UUID timeUuid;

  @ClusteringColumn(3)
  @CqlName("reading_status")
  private String readingStatus;

  @CqlName("book_name")
  private String bookName;

  @CqlName("author_names")
  private List<String> authorNames;

  @CqlName("cover_ids")
  private List<String> coverIds;

  @CqlName("rating")
  private int rating;

  @Transient
  private String coverUrl;

  public BooksByUser() {
    this.timeUuid = Uuids.timeBased();
  }

  public String getCoverUrl() {
    return coverUrl;
  }

  public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public UUID getTimeUuid() {
    return timeUuid;
  }

  public void setTimeUuid(UUID timeUuid) {
    this.timeUuid = timeUuid;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public List<String> getAuthorNames() {
    return authorNames;
  }

  public void setAuthorNames(List<String> authorNames) {
    this.authorNames = authorNames;
  }

  public List<String> getCoverIds() {
    return coverIds;
  }

  public void setCoverIds(List<String> coverIds) {
    this.coverIds = coverIds;
  }

  public String getReadingStatus() {
    return readingStatus;
  }

  public void setReadingStatus(String readingStatus) {
    this.readingStatus = readingStatus;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BooksByUser other = (BooksByUser) obj;
    if (bookId == null) {
      if (other.bookId != null)
        return false;
    } else if (!bookId.equals(other.bookId))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
