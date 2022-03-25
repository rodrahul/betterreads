package com.rahulrode.betterreads.book;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface BookMapper {

  @DaoFactory
  BookDao bookDao();
}
