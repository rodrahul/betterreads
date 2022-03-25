import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Book } from '../models/book.model';
import { BookStorageService } from './book-storage.service';
import { BookService } from './book.service';

@Injectable({
  providedIn: 'root',
})
export class BookResolverService implements Resolve<Book> {
  constructor(
    private bookStorage: BookStorageService,
    private bookService: BookService
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Book | Observable<Book> | Promise<Book> {
    const book = this.bookStorage.getBook();
    const bookId = route.params.id;
    console.log('in book resolver : ', book.name);
    if (book.name == null || book.name == undefined || book.id !== bookId) {
      console.log('book resolver fetching book for bookId: ' + bookId);
      return this.bookService.fetchBook(bookId).pipe(map((resp) => resp.book));
    }

    return book;
  }
}
