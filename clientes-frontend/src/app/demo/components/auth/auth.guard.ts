import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../../service/auth.service'; // Asegúrate de que la ruta de importación es correcta

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    constructor(private authService: AuthService, private router: Router) {}

    canActivate(): boolean {
        if (!this.authService.isLoggedIn()) {
            this.router.navigate(['/login']);
            return false;
        }
        return true;
    }
}
