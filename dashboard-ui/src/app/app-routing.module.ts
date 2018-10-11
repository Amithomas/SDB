import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DatatableComponent } from './datatable/datatable.component';
import { SearchassignComponent } from './searchassign/searchassign.component';
import { BrowserModule } from '@angular/platform-browser';
import { ServerformComponent } from './serverform/serverform.component';
import { UserassigneditComponent } from './userassignedit/userassignedit.component';
import { EditserverComponent } from './editserver/editserver.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
    {
        path: '',
        component: DatatableComponent
    },

    {
        path: 'server-list',
        component: DatatableComponent
    },
    {
        path: 'dashboard-portal',
        component: DashboardComponent
    }, {
        path: 'edit-server-list',
        component: EditserverComponent
    },

    {
        path: 'op',
        component: ServerformComponent
    },
    {
        path: 'assign-server',
        component: SearchassignComponent
    },
    {
        path: 'edit-group-name',
        component: UserassigneditComponent
    },
    {
        path: 'login',
        component: LoginComponent
    }


];

@NgModule( {
    imports: [RouterModule.forRoot( routes, { useHash: true } )],
    exports: [RouterModule]
} )
export class AppRoutingModule { }
