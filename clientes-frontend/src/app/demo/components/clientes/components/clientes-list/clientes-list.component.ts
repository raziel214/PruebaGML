import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Cliente } from '../../../../api/cliente';
import { ClienteService } from '../../../../service/cliente.service';
import {UserService} from "../../../../service/user.service";

@Component({
  selector: 'app-clientes-list',
  templateUrl: './clientes-list.component.html',
  styleUrls: ['./clientes-list.component.scss']
})
export class ClientesListComponent implements OnInit {

    clientes: Cliente[] = [];
    selectedClientes: Cliente[] = [];
    cols: any[];
    newCliente: Cliente = { sharedKey: '', nombre: '',apellido:'',correo:'',telefono:'' };
    clienteDialog: boolean = false;
    submitted: boolean = false;
    statuses: any[] = [];
    deleteClienteDialog: boolean = false;
    deleteClientesDialog: boolean = false;
    selectedCliente: Cliente;
    currentCliente: Cliente = this.createBlankCliente();
    createBlankCliente(): Cliente {
        return { sharedKey: '', nombre: '',apellido:'',correo:'',telefono:'' };
    }

    @ViewChild('dt') table: Table;

    constructor(
        private userService:UserService,
        private clienteService: ClienteService,
        private messageService: MessageService) {}

    ngOnInit() {
        this.cols = [
            { field: 'id', header: 'ID' },
            { field: 'sharedKey', header: 'SharedKey' },
            { field: 'nombre', header: 'Nombres' },
            { field: 'apellido', header: 'Apellidos' },
            { field: 'correo', header: 'Email' },
            { field: 'telefono', header: 'Telefono' },

        ];

        this.loadClientes();
    }

    loadClientes() {



        this.clienteService.getClientes(this.userService.getToken()).subscribe(
            clientes => {
                this.clientes = clientes;
            },
            error => {
                console.error('Error fetching roles');
                this.messageService.add({severity: 'error', summary: 'Error', detail: 'Error fetching clientes'});
            }
        );
    }


    openNew() {
        this.currentCliente = this.createBlankCliente();
        this.submitted = false;
        this.clienteDialog = true;
    }



    editCliente(cliente: Cliente) {
        this.currentCliente  = {...cliente};
        this.clienteDialog = true;
    }

    saveCliente() {
        const token = this.userService.getToken();
        if (!this.currentCliente.id&&this.currentCliente.nombre && /^[a-zA-Z ]*$/.test(this.currentCliente.nombre)
            &&this.currentCliente.apellido && /^[a-zA-Z ]*$/.test(this.currentCliente.nombre)) {
            this.clienteService.createCliente(token, this.currentCliente).subscribe(
                () => {
                    this.clienteDialog = false;
                    this.currentCliente = { sharedKey: '', nombre: '',apellido:'',correo:'',telefono:''};
                    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Cliente created successfully' });
                    this.loadClientes();
                },
                (error) => {
                    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Cliente creation failed: ' + error.message });
                }
            );
        }
        else if(!this.currentCliente.id){
            this.clienteService.updateClienteById(token, this.currentCliente.id, this.currentCliente).subscribe(
                () => {
                    this.clienteDialog = false;
                    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Cliente updated successfully' });
                    this.loadClientes();
                },
                (error) => {
                    // Manejo de errores
                    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Cliente update failed: ' + error.message });
                }
            );
        }
        else {
            // Notifica al usuario que ambos campos son necesarios
            this.messageService.add({ severity: 'warn', summary: 'Warning', detail: 'Porfavor rellenar los campos requeridos.' });
        }

    }



    confirmDeleteClienteSelected(cliente: Cliente) {
        if (confirm(`Estas seguro de Eliminar ${cliente.nombre}?`)) {
            const token = this.userService.getToken();
            this.clienteService.deleteCliente(token, cliente.id).subscribe(
                () => {

                    this.clientes = this.clientes.filter(val => val.id !== cliente.id);
                    // Mostrar mensaje de éxito
                    this.messageService.add({ severity: 'success', summary: 'Successful', detail: `Cliente ${cliente.nombre
                    } deleted.` });
                    this.loadClientes();
                },
                error => {
                    // Manejar errores aquí
                    console.error(`Error deleting cliente ${cliente.nombre}:`, error);
                    this.messageService.add({ severity: 'error', summary: 'Error', detail: `Error deleting Cliente ${cliente.nombre}` });
                }
            );
        }
    }



    deleteSelectedClientes() {
        if (this.selectedClientes && this.selectedClientes.length) {
            const token = this.userService.getToken();
            this.selectedClientes.forEach(cliente => {
                this.clienteService.deleteCliente(token, cliente.id).subscribe(
                    () => {
                        this.clientes = this.clientes.filter(val => val.id !== cliente.id);
                        this.messageService.add({ severity: 'success', summary: 'Successful', detail: `Cliente ${cliente.nombre} Deleted`, life: 3000 });
                    },
                    error => {
                        console.error(`Error deleting Cliente ${cliente.nombre}:`, error);
                        this.messageService.add({ severity: 'error', summary: 'Error', detail: `Error deleting Cliente ${cliente.nombre}` });
                    }
                );
            });

            this.deleteClientesDialog = false; // Close the confirmation dialog
            this.selectedClientes = []; // Reset the selection
            this.loadClientes();
        } else {
            this.messageService.add({ severity: 'warn', summary: 'Warning', detail: 'No  hay Clientes selecionados ' });
        }
    }
}
