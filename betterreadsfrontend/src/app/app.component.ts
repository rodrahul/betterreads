import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'betterreadsfrontend';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.autoLogin();
    this.authService.isLoggedIn
      .pipe(filter((isLoggedIn) => isLoggedIn))
      .subscribe(() => {
        this.router.navigate(['/dashboard']);
      });
  }
}
