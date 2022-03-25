import { Component, Input, OnInit } from '@angular/core';
import { UserBook } from '../models/userBook.model';
import { PhotoService } from '../services/photo.service';

@Component({
  selector: 'app-user-book-status',
  templateUrl: './user-book-status.component.html',
  styleUrls: ['./user-book-status.component.scss'],
})
export class UserBookStatusComponent implements OnInit {
  @Input() userBook: UserBook;
  constructor(public photoService: PhotoService) {}

  ngOnInit(): void {}

  counter(i: number) {
    return new Array(i);
  }

  getReadingStatus(): string {
    const returnedStatus = this.userBook.readingStatus;
    let newStatus = '';
    if (returnedStatus === '0-reading') newStatus = 'Reading';
    else if (returnedStatus === '1-completed') newStatus = 'Completed';
    else newStatus = 'Not Started';

    return newStatus;
  }
}
