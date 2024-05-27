import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ClientesListComponent} from "./components/clientes-list/clientes-list.component";

const routes: Routes = [

    {
        path: '', component: ClientesListComponent
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
