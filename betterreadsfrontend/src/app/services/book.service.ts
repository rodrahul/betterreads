import { BookResponse } from './../models/book-response.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Book } from '../models/book.model';
import { BookStorageService } from './book-storage.service';
import { Constants } from './services-const';
import { UserBook } from '../models/userBook.model';

@Injectable({
  providedIn: 'root',
})
export class BookService {
  constructor(
    private http: HttpClient,
    private bookStore: BookStorageService
  ) {}

  fetchBook(bookId: string): Observable<BookResponse> {
    const bookUrl = Constants.APP_URL + '/book/' + bookId;

    return this.http.get<BookResponse>(bookUrl).pipe(
      tap((book) => {
        this.bookStore.setBook(book.book);
        this.bookStore.setUserBook(book.userBooks);
      })
    );
  }

  searchBooks(search: string): Observable<Book[]> {
    const bookUrl = Constants.APP_URL + '/book/search';

    return this.http
      .get<Book[]>(bookUrl, {
        params: new HttpParams().set('q', search),
      })
      .pipe(tap((books) => this.bookStore.setBookSearch(books)));
  }

  fetchBooks(): Observable<UserBook[]> {
    const bookUrl = Constants.APP_URL + '/book';
    return this.http.get<UserBook[]>(bookUrl).pipe(
      tap((userBooks) => {
        this.bookStore.setUserDashboard(userBooks);
      })
    );
  }
}
