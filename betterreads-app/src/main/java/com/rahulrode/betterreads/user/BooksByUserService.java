package com.rahulrode.betterreads.user;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.core.paging.OffsetPager;
import com.datastax.oss.driver.api.core.paging.OffsetPager.Page;

@RequestScoped
public class BooksByUserService {

  @Inject
  BooksByUserDao booksByUserDao;

  public BooksByUser findByIdBookId(String userId, String bookId) {
    return booksByUserDao.findByIdBookId(userId, bookId).one();
  }

  public List<BooksByUser> findBooksByUser(String userId) {
    OffsetPager pager = new OffsetPager(100);
    PagingIterable<BooksByUser> pi = booksByUserDao.findById(userId);
    Page<BooksByUser> page = pager.getPage(pi, 1);
    return page.getElements();
  }

  public BooksByUser save(BooksByUser booksByUser) {
    return booksByUserDao.save(booksByUser);
  }

  public void update(BooksByUser booksByUser) {
    booksByUserDao.update(booksByUser);
  }

}
