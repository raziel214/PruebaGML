import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import { UserLoginResponse } from '../../demo/api/login-response.interface';
import { environment } from '../../../environments/environment';
import {UserService} from "../../demo/service/user.service";
import { Router } from '@angular/router';
@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private loggedIn: boolean = false;

    constructor(
        private userService: UserService,
        private http: HttpClient,
        private router: Router
        ) {}

    login(userData: { userEmail: string; userPass: string }): Observable<UserLoginResponse> {
        return this.userService.validateUser('<tu-token>', userData).pipe(
            tap(response => {
                if (response.isValid) {
                    this.loggedIn = true;
                    // Guardar token o cualquier otra lógica necesaria
                    this.router.navigate(['/dashboard']);
                }
            })
        );
    }

    logout(): void {
        this.loggedIn = false;
        this.router.navigate(['/login']);
    }

    isLoggedIn(): boolean {
        return this.loggedIn;
    }
    // En auth.service.ts
    setLoggedIn(value: boolean): void {
        this.loggedIn = value;
    }

}
