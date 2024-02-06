import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http = inject(HttpClient);

  public logout(refresh_token: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
    });
    return this.http.post<void>(
      'http://localhost:8080/realms/pgm/protocol/openid-connect/logout',
      new URLSearchParams({
        client_id: 'pgm_manager',
        client_secret: environment.client_secret,
        refresh_token,
      }),
      { headers },
    );
  }

  public login(username: string, password: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
    });

    return this.http.post<{
      access_token: string;
      refresh_token: string;
    }>(
      'http://localhost:8080/realms/pgm/protocol/openid-connect/token',
      new URLSearchParams({
        client_id: 'pgm_manager',
        client_secret: environment.client_secret,
        grant_type: 'password',
        username,
        password,
      }),
      { headers },
    );
  }
}
