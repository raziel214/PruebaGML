// src/app/core/services/token.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
@Injectable({
    providedIn: 'root',
})
export class TokenService {
    private readonly tokenUrl = environment.tokenServiceUrl;

    constructor(private http: HttpClient) { }

    generateToken(credentials: { userEmail: string, userPass: string }): Observable<string> {
        return this.http.post(this.tokenUrl, credentials, { responseType: 'text' });
    }

}
