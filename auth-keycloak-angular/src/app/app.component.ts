import { Component, DestroyRef, inject } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import {
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { tap } from 'rxjs';

import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'auth-keycloak-angular';
  protected refresh_token = '';
  private destroyRef = inject(DestroyRef);
  private authService = inject(AuthService);
  private fb = inject(NonNullableFormBuilder);

  protected loginForm = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });

  login() {
    this.authService
      .login(
        this.loginForm.getRawValue().username,
        this.loginForm.getRawValue().password,
      )
      .pipe(
        takeUntilDestroyed(this.destroyRef),
        tap(resp => {
          localStorage.setItem('ACCESS_TOKEN', resp.access_token);
          localStorage.setItem('REFRESH_TOKEN', resp.refresh_token);
        }),
      )
      .subscribe();
  }

  logout() {
    this.refresh_token = localStorage.getItem('REFRESH_TOKEN')!;
    if (this.refresh_token) {
      this.authService
        .logout(this.refresh_token)
        .pipe(
          tap(() => {
            localStorage.removeItem('ACCESS_TOKEN');
            localStorage.removeItem('REFRESH_TOKEN');
          }),
          takeUntilDestroyed(this.destroyRef),
        )
        .subscribe();
    }
  }
}
