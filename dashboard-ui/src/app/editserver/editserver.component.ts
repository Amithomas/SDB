import { DashboardService } from '../dashboard.service';
import { ServerGroup } from '../model/ServerGroup.model';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { from } from 'rxjs/observable/from';
import { groupBy, mergeMap, toArray } from 'rxjs/operators';


@Component( {
    selector: 'app-datatable',
    templateUrl: './editserver.component.html',
    styleUrls: ['./editserver.component.css']
} )
export class EditserverComponent implements OnInit {
    @ViewChild( MatPaginator ) paginator: MatPaginator;
    @ViewChild( MatSort ) sort: MatSort;
    dataSource: MatTableDataSource<ServerGroup>;
    private serverList: Map<String, Array<ServerGroup>>;
    private servers:ServerGroup[];

    constructor( private dashboardService: DashboardService, private _router: Router ) { }

    /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
    displayedColumns = ['position', 'GroupName', 'ServerId', 'ServerName', 'ServiceName', 'ServerType', 'IP', 'Port'
        , 'Edit', 'Delete'];


    ngOnInit() {
        // this.dataSource = new DatatableDataSource(this.paginator, this.sort);

        this.dashboardService.getServersList().pipe( map(( data: any ) => data ) ).subscribe(( res ) => {
            this.servers =  res;
            this.dataSource = new MatTableDataSource( res );

        }, ( error ) => {
            console.log( error );
        } );

    }

    deleteServer( serverGroup ) {
        this.dashboardService.deleteServer( serverGroup ).subscribe(( data ) => {
            this.servers.splice( this.servers.indexOf( serverGroup ), 1 );
            this.dataSource = new MatTableDataSource( this.servers );
        }, ( error ) => {
            console.log( error );
        } );
    }

    updateServer( serverGroup ) {
        this.dashboardService.setterServerGroup( serverGroup );
        this._router.navigate( ['/op'] );

    }

    newServer() {
        let serverGroup = new ServerGroup();
        this.dashboardService.setterServerGroup( serverGroup );
        this._router.navigate( ['/op'] );

    }

}

