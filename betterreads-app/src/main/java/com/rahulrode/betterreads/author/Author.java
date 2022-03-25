package com.rahulrode.betterreads.author;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

/**
 * CREATE TABLE IF NOT EXISTS main.author_by_id(author_id text PRIMARY KEY,
 * author_name text, personal_name text)
 */

@Entity
@CqlName("author_by_id")
// @PropertyStrategy(mutable = false)
public class Author {

  @PartitionKey
  @CqlName("author_id")
  private String id;

  @CqlName("author_name")
  private String name;

  @CqlName("personal_name")
  private String personalName;

  public Author() {

  }

  public Author(String id, String name, String personalName) {
    this.id = id;
    this.name = name;
    this.personalName = personalName;
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

  public String getPersonalName() {
    return this.personalName;
  }

  public void setPersonalName(String personalName) {
    this.personalName = personalName;
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", name='" + getName() + "'" +
        ", personalName='" + getPersonalName() + "'" +
        "}";
  }

}
