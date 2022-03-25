import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit, OnDestroy {
  public loginValid = true;
  public username = '';
  public password = '';

  private _destroySub$ = new Subject<void>();
  private readonly returnUrl: string;

  constructor(
    private _route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {}

  public ngOnInit(): void {
    // empty
  }

  public ngOnDestroy(): void {
    this._destroySub$.next();
  }

  public onSubmit(form: NgForm): void {
    if (!form.valid) {
      return;
    }
    const username = form.value.username;
    const password = form.value.password;
    console.log('Login form submitted', username, ' ', password);
    this.authService.login(username, password).subscribe(
      (resp) => {
        this.router.navigate(['/dashboard']);
      },
      (errorMsg) => {
        this.loginValid = false;
      }
    );
  }
}
