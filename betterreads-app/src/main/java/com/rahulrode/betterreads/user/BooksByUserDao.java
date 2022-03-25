package com.rahulrode.betterreads.user;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

@Dao
public interface BooksByUserDao {

  @Select(orderBy = {"book_id desc", "reading_status desc"})
  PagingIterable<BooksByUser> findById(String userId);

  @Select
  PagingIterable<BooksByUser> findByIdBookId(String userId, String bookId);

  @Insert
  BooksByUser save(BooksByUser booksByUser);

  @Update
  // @Update(customWhereClause = "reading_status = :reading_status")
  void update(BooksByUser booksByUser);

}
