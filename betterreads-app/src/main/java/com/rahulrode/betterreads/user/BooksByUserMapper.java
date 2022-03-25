package com.rahulrode.betterreads.user;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface BooksByUserMapper {

  @DaoFactory
  BooksByUserDao booksByUserDao();

}
