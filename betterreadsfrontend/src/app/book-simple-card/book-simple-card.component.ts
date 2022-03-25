import { BookStorageService } from './../services/book-storage.service';
import { Component, Input, OnInit } from '@angular/core';
import { Book } from '../models/book.model';
import { PhotoService } from '../services/photo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-simple-card',
  templateUrl: './book-simple-card.component.html',
  styleUrls: ['./book-simple-card.component.scss'],
})
export class BookSimpleCardComponent implements OnInit {
  @Input()
  book: Book;

  constructor(
    public photoService: PhotoService,
    private bookStore: BookStorageService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  navigateToBook() {
    this.router.navigate(['/book', this.book.id]);
    this.bookStore.setBook(null);
  }
}
