import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { UserBook } from '../models/userBook.model';
import { UserBooksService } from '../services/user-books.service';

@Component({
  selector: 'app-user-book-details',
  templateUrl: './user-book-details.component.html',
  styleUrls: ['./user-book-details.component.scss'],
})
export class UserBookDetailsComponent implements OnInit {
  @Input() userBook: UserBook;
  @Input() bookId: string;
  isAuthenticated = false;
  form: FormGroup;

  ratingOptions = [
    { value: 1 },
    { value: 2 },
    { value: 3 },
    { value: 4 },
    { value: 5 },
  ];

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private userBoooksService: UserBooksService
  ) {}

  counter(i: number) {
    return new Array(i);
  }

  ngOnInit(): void {
    this.authService.isLoggedIn.subscribe((isAuthenticated) => {
      this.isAuthenticated = isAuthenticated;
    });

    this.initForm();
  }

  initForm() {
    let userBookDetails = { ...this.userBook };

    this.form = this.formBuilder.group({
      bookId: this.bookId,
      readingStatus: [userBookDetails.readingStatus],
      startedDate: [userBookDetails.startedDate],
      completedDate: [userBookDetails.completedDate],
      rating: [userBookDetails.rating],
    });
  }

  onSubmit(userBookForm: FormGroup): void {
    if (userBookForm.invalid) {
      return;
    }
    const temp = userBookForm.value;
    this.userBoooksService.save(temp).subscribe((resp) => {
      console.log('userbook svc save resp ', resp);
    });
  }
}
