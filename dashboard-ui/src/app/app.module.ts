import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {DashboardService} from './dashboard.service';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './/app-routing.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {SlimLoadingBarModule} from 'ng2-slim-loading-bar';
import {ServerformComponent} from './serverform/serverform.component';
import {FormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SidenavComponent} from './sidenav/sidenav.component';
import {LayoutModule} from '@angular/cdk/layout';
import {
  MatToolbarModule, MatButtonModule, MatSidenavModule,
  MatIconModule, MatListModule, MatGridListModule, MatCardModule, MatMenuModule,
  MatTableModule, MatPaginatorModule, MatSortModule,MatExpansionModule,MatFormFieldModule,MatTooltipModule
} from '@angular/material';
import {DatatableComponent} from './datatable/datatable.component';
import { SearchassignComponent } from './searchassign/searchassign.component';
import { UserassigneditComponent } from './userassignedit/userassignedit.component';
import { EditserverComponent } from './editserver/editserver.component';
import { LoginComponent } from './login/login.component';
import { LoaderComponent } from './loader/loader.component';
import {DialogModule} from 'primeng/dialog';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ServerformComponent,
    SidenavComponent,
    DatatableComponent,
    SearchassignComponent,
    UserassigneditComponent,
    EditserverComponent,
    LoginComponent,
    LoaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    SlimLoadingBarModule,
    FormsModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatTooltipModule,
    DialogModule
  ],
  providers: [DashboardService],
  bootstrap: [AppComponent]
})
export class AppModule {}
