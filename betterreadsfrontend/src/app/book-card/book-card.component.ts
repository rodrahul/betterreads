import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Book } from '../models/book.model';
import { UserBook } from '../models/userBook.model';
import { BookStorageService } from '../services/book-storage.service';
import { PhotoService } from '../services/photo.service';

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.scss'],
})
export class BookCardComponent implements OnInit {
  isLoggedIn: boolean;
  @Input()
  book: Book;
  @Input()
  userBook: UserBook;

  constructor(
    public photoService: PhotoService,
    private bookStore: BookStorageService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // When this component is initialized i.e. we're viewing the book, we need
    //to clear the search result as we don't want search results to be displayed
    this.bookStore.setBookSearch([]);

    // is user logged in
    this.authService.isLoggedIn.subscribe((loggedIn) => {
      this.isLoggedIn = loggedIn;
    });

    console.log('BookCard Component ', this.userBook)
  }
}
