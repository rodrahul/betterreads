import { BookStorageService } from './../services/book-storage.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Unsubscribable } from 'rxjs';
import {
  debounceTime,
  distinctUntilChanged,
  filter,
  map,
  switchMap,
  tap,
} from 'rxjs/operators';
import { Book } from '../models/book.model';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-book-search',
  templateUrl: './book-search.component.html',
  styleUrls: ['./book-search.component.scss'],
})
export class BookSearchComponent implements OnInit, OnDestroy {
  search = new FormControl();
  subs = new Set<Unsubscribable>();
  books: Book[] = [];
  isLoading = false;

  // Update the search form if someone changes the queryparam search
  search$ = this.route.queryParams.pipe(
    map((params) => params['q'] || null),
    distinctUntilChanged(),
    // Set the formcontrol
    tap((search) => {
      if (!this.search?.value || this.search?.value === search) {
        return;
      }
      this.search.setValue = search;
    })
  );

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookService: BookService,
    private bookStore: BookStorageService
  ) {}

  ngOnInit(): void {
    // Subscribe to user search, and update the route
    this.subs.add(
      this.search.valueChanges
        .pipe(
          debounceTime(1000),
          distinctUntilChanged(),
          tap((search) => {
            this.books = [];
            const params = this.route.snapshot?.queryParams;
            if (params['q'] === search || (!params['q'] && !search)) {
              return;
            }

            this.router.navigate(['/'], {
              queryParams: {
                q: search,
              },
            });
          }),
          filter((search) => search !== '' && search !== undefined),
          switchMap((search) => {
            this.isLoading = true;
            return this.bookService.searchBooks(search);
          })
        )
        .subscribe(() => {
          this.isLoading = false;
        })
    );

    this.subs.add(
      this.bookStore.bookSearchChanged.subscribe((books) => {
        console.log('bookstoreage search sub');
        this.books = books;
      })
    );

    this.search.setValue(this.route.snapshot.queryParams['q']);
  }

  clearSearch(): void {
    this.search.setValue('');
    this.bookStore.setBookSearch([]);
  }

  ngOnDestroy(): void {
    this.subs.forEach((x) => x.unsubscribe());
  }
}
