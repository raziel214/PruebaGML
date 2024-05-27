// src/app/features/login/services/user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {forkJoin, Observable, of, switchMap} from 'rxjs';
import { Login } from '../api/login';
import { environment } from '../../../environments/environment';
import {Users} from "../api/users";


@Injectable({
    providedIn: 'root',
})
export class UserService {
    private token:string='';
    constructor(private http: HttpClient, ) {}
    getToken():string{
        return this.token;
    }
    setToken(token: string){
        return this.token=token;
    }

    validateUser(token: string, userData: { userEmail: string; userPass: string }): Observable<Login> {
        const url = environment.userServiceUrl; // Asegúrate de que esta URL es correcta

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`, // Aquí agregas el token al encabezado
            'Accept': 'application/json', // Ajusta según lo que tu API requiere
        });
        return this.http.post<Login>(url, userData, { headers });
    }

    getAllUser(token: string): Observable<Users[]> {
        const url = `${environment.urlBase}users`;
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        });
        return this.http.get<Users[]>(url, { headers });
    }

    getByIdUsers(token:string, userId:number):Observable<Users>{
        const url = `${environment.urlBase}users/${userId}`;
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        });
        return this.http.post<Users>(url, { headers });
    }
    createUsers(token:string, userData:{userName: string,
        userLastName: string,userEmail: string,userRolId:	number,
        dateCreation:string, dateUpdate:string, state: number,
        first: number,recoveryPass: number,ciudadId:number }):Observable<Users>{

        const url = `${environment.urlBase}users`;
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        });
        return this.http.post<Users>(url, userData, { headers: headers });

    }
    updateUsers(token: string, userId: number, userData: Users):Observable<Users>{

        const url = `${environment.urlBase}users/${userId}`; // Ajusta para que apunte a tu API
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        });

        // userData es el objeto que contiene los datos actualizados del usuario
        return this.http.put<Users>(url, userData, { headers });
    }
    deleteUsers(token: string, userId: number):Observable<any>{
        const url = `${environment.urlBase}users/${userId}`;
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        });
        return this.http.delete(url, { headers });
    }


}
