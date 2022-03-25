import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserBook } from '../models/userBook.model';
import { Constants } from './services-const';

@Injectable({
  providedIn: 'root',
})
export class UserBooksService {
  constructor(private http: HttpClient) {}

  save(userBooks: UserBook): Observable<any> {
    console.log('saving user books ', userBooks);
    const bookUrl = Constants.APP_URL + '/userbooks/save';
    return this.http.post(bookUrl, userBooks);
  }
}
