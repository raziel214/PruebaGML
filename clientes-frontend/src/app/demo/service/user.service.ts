import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';
import { CreateUserRequest, UpdateUserRequest, User } from '../api/users';

@Injectable({ providedIn: 'root' })
export class UserService {

    private readonly baseUrl = `${environment.apiBaseUrl}/api/users`;

    constructor(private http: HttpClient) {}

    getAll(): Observable<User[]> {
        return this.http.get<User[]>(this.baseUrl);
    }

    getById(id: number): Observable<User> {
        return this.http.get<User>(`${this.baseUrl}/${id}`);
    }

    getByEmail(email: string): Observable<User> {
        return this.http.get<User>(`${this.baseUrl}/by-email`, {
            params: new HttpParams().set('email', email)
        });
    }

    create(request: CreateUserRequest): Observable<User> {
        return this.http.post<User>(this.baseUrl, request);
    }

    update(id: number, request: UpdateUserRequest): Observable<User> {
        return this.http.put<User>(`${this.baseUrl}/${id}`, request);
    }

    delete(id: number): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }
}
