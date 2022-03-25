package com.rahulrode.betterreadsloader.author;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class AuthorService {

  @Inject
  AuthorDao authorDao;

  public List<Author> getAll() {
    return authorDao.findAll().all();
  }

  public Author findById(String authorId) {
    return authorDao.findById(authorId);
  }

  public void create(Author author) {
    authorDao.save(author);
  }

  public void update(Author author) {
    authorDao.update(author);
  }

  public void delete(String authorId) {
    authorDao.deleteById(authorId);
  }

  public void delete(Author author) {
    authorDao.delete(author);
  }

}
