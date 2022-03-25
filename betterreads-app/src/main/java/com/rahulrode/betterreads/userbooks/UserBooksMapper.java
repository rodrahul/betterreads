package com.rahulrode.betterreads.userbooks;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface UserBooksMapper {

  @DaoFactory
  UserBooksDao userbooksDao();
  
}
