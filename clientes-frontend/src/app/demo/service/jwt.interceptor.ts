import { Injectable } from '@angular/core';
import {
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest
} from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthService } from './auth.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService) {}

    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
        if (request.url.includes('/public/api/auth/login')) {
            return next.handle(request);
        }

        const token = this.authService.getToken();
        if (!token) {
            return next.handle(request);
        }

        const authorized = request.clone({
            setHeaders: { Authorization: `Bearer ${token}` }
        });
        return next.handle(authorized);
    }
}
