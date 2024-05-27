import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { NotfoundComponent } from './demo/components/notfound/notfound.component';
import { AppLayoutComponent } from "./layout/app.layout.component";
import {LoginComponent} from "./demo/components/auth/login/components/login.component";
import { AuthGuard } from './demo/components/auth/auth.guard';
@NgModule({
    imports: [
        RouterModule.forRoot([
            { path: 'login', component: LoginComponent },

            {
                path: '', component: AppLayoutComponent,
                canActivate: [AuthGuard],

                children: [
                    { path: 'dashboard', loadChildren: () => import('./demo/components/dashboard/dashboard.module').then(m => m.DashboardModule) },
                    { path: 'clientes', loadChildren: () => import('./demo/components/clientes/clientes.module').then(m => m.ClientesModule) },
                    { path: 'tickets', loadChildren: () => import('./demo/components/tickets/tickets.module').then(m => m.TicketsModule) }

                ]
            },
            { path: 'auth', loadChildren: () => import('./demo/components/auth/auth.module').then(m => m.AuthModule) },
            { path: 'landing', loadChildren: () => import('./demo/components/landing/landing.module').then(m => m.LandingModule) },
            { path: 'notfound', component: NotfoundComponent },
            { path: '**', redirectTo: '/notfound' },
        ], { scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled', onSameUrlNavigation: 'reload' })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
