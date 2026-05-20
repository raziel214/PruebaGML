import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';
import { Cliente } from '../api/cliente';

@Injectable({ providedIn: 'root' })
export class ClienteService {

    private readonly baseUrl = `${environment.apiBaseUrl}/api/cliente`;

    constructor(private http: HttpClient) {}

    getAll(): Observable<Cliente[]> {
        return this.http.get<Cliente[]>(this.baseUrl);
    }

    getById(id: number): Observable<Cliente> {
        return this.http.get<Cliente>(`${this.baseUrl}/${id}`);
    }

    getBySharedKey(sharedKey: string): Observable<Cliente> {
        return this.http.get<Cliente>(`${this.baseUrl}/by-shared-key/${encodeURIComponent(sharedKey)}`);
    }

    create(cliente: Cliente): Observable<Cliente> {
        return this.http.post<Cliente>(this.baseUrl, cliente);
    }

    update(id: number, cliente: Cliente): Observable<Cliente> {
        return this.http.put<Cliente>(`${this.baseUrl}/${id}`, cliente);
    }

    delete(id: number): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }
}
