package com.rahulrode.betterreadsloader.author;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface AuthorMapper {

  @DaoFactory
  AuthorDao authorDao();
  
}
