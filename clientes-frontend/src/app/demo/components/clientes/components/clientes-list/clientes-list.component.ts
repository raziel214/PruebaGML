import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';

import { Cliente } from '../../../../api/cliente';
import { ClienteService } from '../../../../service/cliente.service';

@Component({
    selector: 'app-clientes-list',
    templateUrl: './clientes-list.component.html',
    styleUrls: ['./clientes-list.component.scss']
})
export class ClientesListComponent implements OnInit {

    clientes: Cliente[] = [];
    selectedClientes: Cliente[] = [];
    cols: { field: string; header: string }[] = [];
    currentCliente: Cliente = this.createBlankCliente();
    selectedCliente?: Cliente;

    clienteDialog = false;
    deleteClienteDialog = false;
    deleteClientesDialog = false;
    submitted = false;

    @ViewChild('dt') table?: Table;

    constructor(
        private clienteService: ClienteService,
        private messageService: MessageService
    ) {}

    ngOnInit(): void {
        this.cols = [
            { field: 'id', header: 'ID' },
            { field: 'sharedKey', header: 'SharedKey' },
            { field: 'nombre', header: 'Nombres' },
            { field: 'apellido', header: 'Apellidos' },
            { field: 'correo', header: 'Email' },
            { field: 'telefono', header: 'Telefono' }
        ];
        this.loadClientes();
    }

    loadClientes(): void {
        this.clienteService.getAll().subscribe({
            next: clientes => this.clientes = clientes,
            error: () => this.messageService.add({
                severity: 'error', summary: 'Error', detail: 'Error fetching clientes'
            })
        });
    }

    openNew(): void {
        this.currentCliente = this.createBlankCliente();
        this.submitted = false;
        this.clienteDialog = true;
    }

    editCliente(cliente: Cliente): void {
        this.currentCliente = { ...cliente };
        this.clienteDialog = true;
    }

    saveCliente(): void {
        this.submitted = true;
        if (!this.isClienteValid(this.currentCliente)) {
            this.messageService.add({
                severity: 'warn', summary: 'Warning', detail: 'Por favor rellenar los campos requeridos.'
            });
            return;
        }

        const action$ = this.currentCliente.id
            ? this.clienteService.update(this.currentCliente.id, this.currentCliente)
            : this.clienteService.create(this.currentCliente);

        action$.subscribe({
            next: () => {
                this.clienteDialog = false;
                this.currentCliente = this.createBlankCliente();
                this.messageService.add({
                    severity: 'success',
                    summary: 'Success',
                    detail: 'Cliente saved successfully'
                });
                this.loadClientes();
            },
            error: err => this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Cliente save failed: ' + (err?.error?.message ?? err.message)
            })
        });
    }

    confirmDeleteClienteSelected(cliente: Cliente): void {
        if (!cliente?.id) return;
        if (!confirm(`¿Estás seguro de eliminar a ${cliente.nombre}?`)) return;

        this.clienteService.delete(cliente.id).subscribe({
            next: () => {
                this.clientes = this.clientes.filter(c => c.id !== cliente.id);
                this.messageService.add({
                    severity: 'success',
                    summary: 'Successful',
                    detail: `Cliente ${cliente.nombre} deleted.`
                });
            },
            error: () => this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: `Error deleting Cliente ${cliente.nombre}`
            })
        });
    }

    deleteSelectedClientes(): void {
        if (!this.selectedClientes?.length) {
            this.messageService.add({
                severity: 'warn', summary: 'Warning', detail: 'No hay clientes seleccionados.'
            });
            return;
        }
        this.selectedClientes.forEach(cliente => {
            if (cliente.id) {
                this.confirmDeleteClienteSelected(cliente);
            }
        });
        this.deleteClientesDialog = false;
        this.selectedClientes = [];
    }

    private createBlankCliente(): Cliente {
        return { sharedKey: '', nombre: '', apellido: '', correo: '', telefono: '' };
    }

    private isClienteValid(cliente: Cliente): boolean {
        const nameRegex = /^[a-zA-Z ]+$/;
        return !!cliente.sharedKey
            && !!cliente.nombre && nameRegex.test(cliente.nombre)
            && !!cliente.apellido && nameRegex.test(cliente.apellido)
            && !!cliente.correo
            && !!cliente.telefono;
    }
}
