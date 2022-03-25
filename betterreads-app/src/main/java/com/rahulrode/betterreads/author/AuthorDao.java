package com.rahulrode.betterreads.author;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

@Dao
public interface AuthorDao {

  @Select
  PagingIterable<Author> findAll();

  @Select
  Author findById(String authorId);

  @Insert
  void save(Author author);

  @Update
  void update(Author author);

  @Delete
  void delete(Author author);

  @Delete(entityClass = Author.class)
  void deleteById(String authorId);

}
