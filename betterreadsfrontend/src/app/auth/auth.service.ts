import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { BookStorageService } from '../services/book-storage.service';
import { User } from './user.model';

export interface AuthResponseData {
  access_token: string;
  expires_in: number;
  refresh_expires_in: string;
  refresh_token: string;
  token_type: string;
  session_state: string;
  scope: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loginUrl =
    'http://localhost:8180/auth/realms/quarkus/protocol/openid-connect/token';

  isLoggedIn = new BehaviorSubject<boolean>(false);
  user = new BehaviorSubject<User>(null);

  constructor(
    private http: HttpClient,
    private router: Router,
    private bookStore: BookStorageService
  ) {}

  login(username: string, password: string): Observable<AuthResponseData> {
    let formData = new URLSearchParams();
    formData.set('client_id', 'quarkus-app');
    formData.set('grant_type', 'password');
    formData.set('username', username);
    formData.set('password', password);

    let options = {
      headers: new HttpHeaders().set(
        'Content-Type',
        'application/x-www-form-urlencoded'
      ),
    };

    return this.http
      .post<AuthResponseData>(this.loginUrl, formData.toString(), options)
      .pipe(
        catchError(this.handleError),
        tap((resp) => {
          this.handleAuthentication(resp.expires_in, resp.access_token);
        })
      );
  }

  logout() {
    this.isLoggedIn.next(false);
    this.user.next(null);
    localStorage.removeItem('userData');
    this.bookStore.clearStore();
    // this.router.navigate(['/']);
  }

  private handleAuthentication(expiresIn: number, authToken: string): void {
    const expirationDate = new Date(new Date().getTime() + +expiresIn * 1000);
    const user = new User(expirationDate, authToken);
    this.user.next(user);

    this.isLoggedIn.next(true);
    localStorage.setItem('userData', JSON.stringify(user));
  }

  private handleError(errorResp: HttpErrorResponse): Observable<any> {
    return throwError('Username/Password is invalid');
  }

  autoLogin() {
    const userData: {
      _tokenExpirationDate: Date;
      _token: string;
    } = JSON.parse(localStorage.getItem('userData'));

    if (!userData) return;
    const loadedUser = new User(userData._tokenExpirationDate, userData._token);

    this.isLoggedIn.next(true);
    this.user.next(loadedUser);
  }
}
