
import { catchError, map } from 'rxjs/operators';
import { ServerGroup } from './model/ServerGroup.model';
import { UserInGroup } from './model/UserInGroup.model';
import { Services } from './model/Services.model';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { RequestOptions, Request, RequestMethod } from '@angular/http';


const httpOptions = {
    headers: new HttpHeaders( {
        'Content-Type': 'application/json'
    } )

};

@Injectable( {
    providedIn: 'root'
} )
export class DashboardService {


    constructor( private _http: HttpClient ) { }

    private dashboardUrl = 'http://localhost:8080/dashboard-portal';


    private userInGroup = new UserInGroup();
    private serverGroup = new ServerGroup();

    getServicesList( sg: ServerGroup ) {
       // return this._http.get( './getServicesList/' + sg, httpOptions ).pipe(
       //     map(( response: Response ) => response ), catchError( this.errorHandler ), );
        //return this._http.get( './getServicesList/'+sg , httpOptions).pipe( map( result => result ) );
        return this._http.get( './services', httpOptions).pipe( map( result => result ) );
    }

    getServersList() {
        return this._http.get( './getServers', httpOptions ).pipe(
            map(( response: Response ) => response ), catchError( this.errorHandler ), );
    }

    getServers() {
        return this._http.get( './serverlist/axm283', httpOptions ).pipe(
            map(( response: Response ) => response ), catchError( this.errorHandler ), );
    }

    getServer( serverId: String ) {
        return this._http.get( './server/' + serverId ).pipe( map(( response: Response ) => response ),
            catchError( this.errorHandler ), );
    }

    deleteServer( sg: ServerGroup ) {
        return this._http.post( './deleteserver/', sg ).pipe( map(( response: Response ) => response ),
            catchError( this.errorHandler ), );
    }
    
    
    startService( ser ) {
        return this._http.get( './startservice?sname='+ ser ).pipe( map(( response: Response ) => response ),
            catchError( this.errorHandler ), );
    }
    
    /*stopService( ser: Services ) {
        return this._http.post( 'http://localhost:8090/stopservice', ser ).pipe( map(( response: Response ) => response ),
            catchError( this.errorHandler ), );
    }*/

    stopService( ser ) {
        return this._http.get( './stopservice?sname='+ ser ).pipe( map(( response: Response ) => response ),
            catchError( this.errorHandler ), );
    }


    createServer( sg: ServerGroup ) {
        return this._http.post( './addserver/', sg ).pipe( map(( response: Response ) => response ),
            catchError( this.errorHandler ), );
    }

    updateServer( sg: ServerGroup ) {
        return this._http.put( './updateserver/', sg ).pipe( map(( response: Response ) => response ),
            catchError( this.errorHandler ), );
    }

    errorHandler( error: Response ) {
        return throwError( error || 'SERVER ERROR' );
    }

    setterUserInGroup( userinGroup: UserInGroup ) {
        this.userInGroup = userinGroup;
    }

    getterUserInGroup() {
        return this.userInGroup;
    }

    setterServerGroup( sg: ServerGroup ) {
        this.serverGroup = sg;
    }


    getterServerGroup() {
        return this.serverGroup;
    }

    deleteByUserIdAndGroupName( uig: UserInGroup ) {

        return this._http.post( './deleteuseridgroupname/', uig ).pipe( map(( response: Response ) => response ),
            catchError( this.errorHandler ), );
    }

    createUserInGroup( uig: UserInGroup ) {
        return this._http.post( './adduseringroup/', uig ).pipe( map(( response: Response ) => response ),
            catchError( this.errorHandler ), );
    }

    updateUserInGroup( uig: UserInGroup ) {
        return this._http.put( './updateuseringroup/', uig ).pipe( map(( response: Response ) => response ),
            catchError( this.errorHandler ), );
    }


    displayDiskUsage( server ) {
        return this._http.get( './diskstatus/' ).pipe( map( result => result ) );
    }

    displayCpuUsage( server ) {
        return this._http.get( './cpustatus' ).pipe( map( result => result ) );
    }

    displayAvailibility( server ) {
        return this._http.get( './assets/js/test-data/server_A_serveruptime.json' ).pipe( map( result => result ) );
    }


    getAllUserIds() {
        return this._http.get( './userIds', httpOptions ).pipe(
            map(( response: Response ) => response ), catchError( this.errorHandler ), );
    }

    search( q: string ): Observable<any> {
        if ( !q || q === '*' ) {
            q = '';
        } else {
            q = q.toLowerCase();
        }

        return this.getAllUserIds().pipe( map(( data: any ) => data
            .map( item => !!localStorage['userInGroup' + item.userId] ?
                JSON.parse( localStorage['userInGroup' + item.userId] ) : item )
            .filter( item => JSON.stringify( item ).toLowerCase().includes( q ) )
        ) );
    }

    get( userId: string ) {
        return this.getAllUserIds().pipe( map(( all: any ) => {
            if ( localStorage['userInGroup' + userId] ) {
                return JSON.parse( localStorage['userInGroup' + userId] );
            }
            return all.find( e => e.userId === userId );
        } ) );
    }

    save( userInGroup: UserInGroup ) {
        localStorage['userInGroup' + userInGroup.userId] = JSON.stringify( userInGroup );;
    }


}



