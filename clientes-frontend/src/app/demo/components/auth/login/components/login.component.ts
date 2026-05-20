import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { AuthService } from '../../../../service/auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {

    valCheck: string[] = ['remember'];
    loginForm!: FormGroup;
    loading = false;
    errorMessage: string | null = null;

    constructor(
        public layoutService: LayoutService,
        private fb: FormBuilder,
        private authService: AuthService,
        private router: Router
    ) {}

    ngOnInit(): void {
        this.loginForm = this.fb.group({
            useremail: ['', [Validators.required, Validators.email]],
            password: ['', Validators.required]
        });
    }

    onSubmit(): void {
        if (this.loginForm.invalid || this.loading) {
            return;
        }
        this.loading = true;
        this.errorMessage = null;

        const credentials = {
            userEmail: this.loginForm.value.useremail,
            password: this.loginForm.value.password
        };

        this.authService.login(credentials).subscribe({
            next: () => {
                this.loading = false;
                this.router.navigate(['/clientes']);
            },
            error: (err) => {
                this.loading = false;
                this.errorMessage = err?.error?.message ?? 'Credenciales inválidas';
            }
        });
    }
}
