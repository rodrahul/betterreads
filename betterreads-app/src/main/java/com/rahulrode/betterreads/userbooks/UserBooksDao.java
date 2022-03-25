package com.rahulrode.betterreads.userbooks;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

@Dao
public interface UserBooksDao {

  @Select
  UserBooks findById(String userId, String bookId);

  /**
   * The method will return null if the insertion succeeded, or the existing
   * entity if it failed.
   * 
   * @param userbooks
   * @return
   */
  @Insert
  UserBooks save(UserBooks userbooks);

  @Update
  void update(UserBooks userbooks);

}
