import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientesRoutingModule } from './clientes-routing.module';
import { ClientesListComponent } from './components/clientes-list/clientes-list.component';
import {TableModule} from "primeng/table";

import {MessageService} from "primeng/api";
import { FileUploadModule } from 'primeng/fileupload';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { RatingModule } from 'primeng/rating';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DropdownModule } from 'primeng/dropdown';
import { RadioButtonModule } from 'primeng/radiobutton';
import { InputNumberModule } from 'primeng/inputnumber';
import { DialogModule } from 'primeng/dialog';
import {TicketslistRoutingModule} from "../tickets/ticketlist/ticketslist-routing.module";
import {FormsModule} from "@angular/forms";
@NgModule({
  declarations: [
    ClientesListComponent
  ],
  imports: [
    CommonModule,
    ClientesRoutingModule,
      TableModule,
      FileUploadModule,
      FormsModule,
      ButtonModule,
      RippleModule,
      ToastModule,
      ToolbarModule,
      RatingModule,
      InputTextModule,
      InputTextareaModule,
      DropdownModule,
      RadioButtonModule,
      InputNumberModule,
      DialogModule
  ],
    providers: [
        MessageService,
    ],

})
export class ClientesModule { }
