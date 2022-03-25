import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../models/book.model';
import { UserBook } from '../models/userBook.model';
import { BookStorageService } from '../services/book-storage.service';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss'],
})
export class BookComponent implements OnInit {
  bookId: string;
  book: Book = null;

  userBook: UserBook = null;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private bookStore: BookStorageService
  ) {}

  ngOnInit(): void {
    this.book = this.bookStore.getBook();
    this.userBook = this.bookStore.getUserBook();
    this.bookId = this.route.snapshot.params['bookId'];
  }
}
