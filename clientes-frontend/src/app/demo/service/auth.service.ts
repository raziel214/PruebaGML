import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, tap } from 'rxjs';

import { environment } from '../../../environments/environment';
import { LoginRequest } from '../api/login-request';
import { LoginResponse } from '../api/login-response';

const TOKEN_STORAGE_KEY = 'clientes_access_token';

@Injectable({ providedIn: 'root' })
export class AuthService {

    private readonly loginUrl = `${environment.apiBaseUrl}/public/api/auth/login`;
    private readonly authenticated$ = new BehaviorSubject<boolean>(this.hasToken());

    constructor(private http: HttpClient, private router: Router) {}

    login(credentials: LoginRequest): Observable<LoginResponse> {
        return this.http.post<LoginResponse>(this.loginUrl, credentials).pipe(
            tap(response => this.storeToken(response.accessToken))
        );
    }

    logout(): void {
        localStorage.removeItem(TOKEN_STORAGE_KEY);
        this.authenticated$.next(false);
        this.router.navigate(['/login']);
    }

    getToken(): string | null {
        return localStorage.getItem(TOKEN_STORAGE_KEY);
    }

    isAuthenticated(): boolean {
        return this.hasToken();
    }

    authenticationState(): Observable<boolean> {
        return this.authenticated$.asObservable();
    }

    private storeToken(token: string): void {
        localStorage.setItem(TOKEN_STORAGE_KEY, token);
        this.authenticated$.next(true);
    }

    private hasToken(): boolean {
        return !!localStorage.getItem(TOKEN_STORAGE_KEY);
    }
}
