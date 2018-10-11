import { DashboardService } from '../dashboard.service';
import { ServerGroup } from '../model/ServerGroup.model';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import { DatatableDataSource } from './datatable-datasource';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { from } from 'rxjs/observable/from';
import { groupBy, mergeMap, toArray } from 'rxjs/operators';


@Component( {
    selector: 'app-datatable',
    templateUrl: './datatable.component.html',
    styleUrls: ['./datatable.component.css']
} )
export class DatatableComponent implements OnInit {
    @ViewChild( MatPaginator ) paginator: MatPaginator;
    @ViewChild( MatSort ) sort: MatSort;
    dataSource: MatTableDataSource<ServerGroup>;
    private serverList: Map<String, Array<ServerGroup>>;
    private servers;

    constructor( private dashboardService: DashboardService, private _router: Router ) { }



    getKeys( map: Map<String, Array<ServerGroup>> ) {
        return Object.keys( map );
    }

    ngOnInit() {
        // this.dataSource = new DatatableDataSource(this.paginator, this.sort);

        this.dashboardService.getServers().pipe( map(( data: any ) => data ) ).subscribe(( res ) => {
            this.serverList = res;

        }, ( error ) => {
            console.log( error );
        } );

    }

    deleteServer( serverGroup ) {
        this.dashboardService.deleteServer( serverGroup ).subscribe(( data ) => {
            this.dataSource.data.splice( this.dataSource.data.indexOf( serverGroup ), 1 );
        }, ( error ) => {
            console.log( error );
        } );
    }

    showDashBoard( serverGroup ) {
        this.dashboardService.setterServerGroup( serverGroup );
        this._router.navigate( ['/dashboard-portal'] );


    }

    newServer() {
        let serverGroup = new ServerGroup();
        this.dashboardService.setterServerGroup( serverGroup );
        this._router.navigate( ['/op'] );

    }

}
