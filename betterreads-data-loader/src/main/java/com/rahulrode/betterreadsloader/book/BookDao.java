package com.rahulrode.betterreadsloader.book;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

@Dao
public interface BookDao {

  @Select
  PagingIterable<Book> findAll();

  @Select
  Book findById(String bookId);

  @Insert
  void save(Book book);

  @Update
  void update(Book book);

  @Delete
  void delete(Book book);

  @Delete(entityClass = Book.class)
  void deleteById(String bookId);

}
