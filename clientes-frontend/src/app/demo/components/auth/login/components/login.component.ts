import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { Router } from "@angular/router";
import { TokenService } from '../../../../../demo/service/token.service';
import { UserService } from '../../../../../demo/service/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserLoginResponse } from "../../../../api/login-response.interface";
import { AuthService } from '../../../../service/auth.service'; // Import RolesService


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {
    valCheck: string[] = ['remember'];
    password!: string;
    loginForm: FormGroup;

    constructor(
        private authService: AuthService,
        public layoutService: LayoutService,
        private fb: FormBuilder,
        private tokenService: TokenService,
        private userService: UserService,
        private router: Router
    ) {}

    ngOnInit() {
        this.loginForm = this.fb.group({
            useremail: ['', [Validators.required, Validators.email]],
            password: ['', Validators.required]
        });
    }

    onSubmit() {
        if (this.loginForm.valid) {
            const userCredentials = {
                userEmail: this.loginForm.get('useremail').value,
                userPass: this.loginForm.get('password').value
            };

            this.tokenService.generateToken(userCredentials).subscribe(
                token => {
                    this.userService.validateUser(token, userCredentials).subscribe(
                        (userResponse: UserLoginResponse) => {

                                        this.authService.setLoggedIn(true);
                                        this.userService.setToken(token);
                                        this.router.navigate(['/clientes']);
                        },
                        userError => {
                            console.error('Error validating user:', userError);
                        }
                    );
                },
                tokenError => {
                    console.error('Error generating token:', tokenError);
                }
            );
        }
    }
}
