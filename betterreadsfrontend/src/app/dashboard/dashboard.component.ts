import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { UserBook } from '../models/userBook.model';
import { BookStorageService } from '../services/book-storage.service';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  authenticated: Observable<boolean>;
  userBooks: UserBook[] = [];

  constructor(
    private bookService: BookService,
    private bookStore: BookStorageService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.authenticated = this.authService.isLoggedIn.asObservable();

    this.bookService
      .fetchBooks()
      .subscribe((books) => (this.userBooks = books));

    // this can be avoided by using resolver
    this.bookStore.userBooksDashboard.subscribe(
      (books) => (this.userBooks = books)
    );
  }
}
