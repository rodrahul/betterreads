import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Book } from '../models/book.model';
import { UserBook } from '../models/userBook.model';

@Injectable({
  providedIn: 'root',
})
export class BookStorageService {
  private book: Book;
  private bookSearchResults: Book[];
  private userBook: UserBook;
  private userDashboard: UserBook[];

  bookChanged = new Subject<Book>();
  bookSearchChanged = new Subject<Book[]>();
  userBooksDashboard = new Subject<UserBook[]>();

  setBook(book: Book) {
    if (book) {
      console.log('setting book');
      this.book = book;
    } else {
      this.book = null;
    }
  }

  getBook(): Book {
    return { ...this.book };
  }

  setBookSearch(books: Book[]) {
    this.bookSearchResults = books;
    this.bookSearchChanged.next(books);
  }

  setUserBook(userBook: UserBook) {
    if (userBook) {
      this.userBook = userBook;
    } else {
      this.userBook = null;
    }
  }

  getUserBook(): UserBook {
    return { ...this.userBook };
  }

  setUserDashboard(userBooks: UserBook[]) {
    if (userBooks) {
      this.userDashboard = userBooks;
    } else {
      this.userDashboard = null;
    }
    this.userBooksDashboard.next(userBooks);
  }

  getUserDashboard(): UserBook[] {
    return { ...this.userDashboard };
  }

  clearStore() {
    // this.setBook(null);
    this.setUserBook(null);
    this.setUserDashboard([]);
  }
}
