import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TicketlistComponent } from './ticketlist.component';

@NgModule({
	imports: [RouterModule.forChild([
		{ path: '', component: TicketlistComponent }
	])],
	exports: [RouterModule]
})
export class TicketslistRoutingModule { }
