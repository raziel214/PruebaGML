import { environment } from '../../../environments/environment';
import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Cliente} from "../api/cliente";
@Injectable({
    providedIn: 'root',
})
export class ClienteService {
    constructor(private http: HttpClient) {}

    getClientes(token: string): Observable<Cliente[]> {
        const url = `${environment.urlBase}cliente`;

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
            'Accept': 'application/json', // Adjust as needed
        });
        return this.http.get<Cliente[]>(url, { headers });
    }

    getClienteById(token: string, clienteId: number): Observable<Cliente> {
        const url = `${environment.urlBase}/cliente/${clienteId}`; // Asegúrate de que la URL es correcta

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        });

        return this.http.post<Cliente>(url, {}, { headers: headers });
    }
    updateClienteById(token: string, clienteId: number,clienteData:Cliente):Observable<Cliente>{
        const url = `${environment.urlBase}/cliente/${clienteId}`;
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        });
        return this.http.put<Cliente>(url,clienteData,  { headers: headers });
    }
    createCliente(token: string, clienteData: { sharedKey:	string; nombre:string; apellido:string; correo:string;telefono:string; }): Observable<Cliente> {
        const url = `${environment.urlBase}cliente`;
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        });

        // Asegúrate de enviar `roleData` como el cuerpo de la solicitud POST
        return this.http.post<Cliente>(url, clienteData, { headers: headers });
    }
    deleteCliente(token: string, clienteId: number): Observable<any> {
        const url = `${environment.urlBase}cliente/${clienteId}`; // Asegúrate de que la URL es correcta
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        });
        return this.http.delete(url, { headers });
    }
}
