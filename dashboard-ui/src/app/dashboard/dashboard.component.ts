import { Component } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointState, BreakpointObserver } from '@angular/cdk/layout';

import { DashboardService } from '../dashboard.service';
import {Observable} from 'rxjs/Rx';
import {
    ActivatedRoute, Event,
    NavigationCancel,
    NavigationEnd,
    NavigationError,
    NavigationStart,
    Router
} from '@angular/router';
import { Chart } from 'chart.js';
import { ServerGroup } from '../model/ServerGroup.model';
import { Services } from '../model/Services.model';
import { MatTableDataSource } from '@angular/material/table';

@Component( {
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
} )


export class DashboardComponent {
    panelOpenState = false;
    dashBoardData: ServerGroup;
    pieDiskUsageChart = [];
    lineCpuUsageChart = [];
    lineAvailibilityChart = [];
    services: Services[];
    runningServicesData: MatTableDataSource<Services>;
    stoppedServicesData: MatTableDataSource<Services>;
    loading = false;
    //runningServices: Services[];
    //stoppedServices: Services[];
    runningServices: any[];
    stoppedServices: any[];
    servicesdetails: any[];
    /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
    displayRunningServicesColumns = ['position', 'ServiceName', 'Status', 'Details', 'ServiceType', 'ServerType', 'StopService'];
    displayStoppedServicesColumns = ['position', 'ServiceName', 'Status', 'Details', 'ServiceType', 'ServerType', 'StartService'];

    private isLoading: boolean = false;
    private displaystopdialog: boolean = false;
    private displaystartdialog: boolean = false;
    
    constructor( private breakpointObserver: BreakpointObserver, private route: Router, private data: DashboardService ) {
        this.route.events.subscribe(( event: Event ) => {
            switch ( true ) {
                case event instanceof NavigationStart: {
                    this.loading = true;
                    break;
                }

                case event instanceof NavigationEnd: {
                    // trick the Router into believing it's last link wasn't previously loaded
                    this.route.navigated = false;
                    // if you need to scroll back to top, here is the right place
                    window.scrollTo( 0, 0 );
                }
                case event instanceof NavigationCancel:
                case event instanceof NavigationError: {
                    this.loading = false;
                    break;
                }
                default: {
                    break;
                }
            }
        } );
    }

    showPopup(servicesdetails,status){
       if(status == 'start'){
           this.displaystartdialog = true;
       } else if(status == 'stop') {
           this.displaystopdialog=true;
}
this.servicesdetails=servicesdetails;
}

startService( ) {

        this.isLoading = true;

        this.data.startService( this.servicesdetails['servicename'] ).subscribe(( data ) => {

            if(data){

            this.stoppedServices.splice( this.stoppedServices.indexOf( this.servicesdetails ), 1 );

            this.stoppedServicesData = new MatTableDataSource( this.stoppedServices );

            this.runningServices.push( this.servicesdetails );

            this.runningServicesData = new MatTableDataSource( this.runningServices );

            this.isLoading = false;

            this.displaystartdialog = false;

            }

        }, ( error ) => {

            console.log( error );

        } );

    }

 

   

 

 

    stopService() {

        this.isLoading = true;

        this.data.stopService( this.servicesdetails['servicename'] ).subscribe(( data ) => {

            if(data){

            this.runningServices.splice( this.runningServices.indexOf( this.servicesdetails ), 1 );

            this.runningServicesData = new MatTableDataSource( this.runningServices );

            this.stoppedServices.push( this.servicesdetails );

            this.stoppedServicesData = new MatTableDataSource( this.stoppedServices );

            this.isLoading = false;

            this.displaystopdialog = false;

            }

        }, ( error ) => {

            console.log( error );

        } );

 

    }

    

    /*stopService(services) { 
        this.isLoading = true;
        this.data.stopService( services ).subscribe(( data ) => {
            if(data){
            this.runningServices.splice( this.runningServices.indexOf( services ), 1 );
            this.runningServicesData = new MatTableDataSource( this.runningServices );
            this.stoppedServices.push( services );
            this.stoppedServicesData = new MatTableDataSource( this.stoppedServices );
            this.isLoading = false;
            }
        }, ( error ) => {
            console.log( error );
        } );

    }*/

  
    ngOnInit() {

        this.dashBoardData = this.data.getterServerGroup();
        this.data.getServicesList( this.dashBoardData ).pipe( map(( data: any ) => data ) ).subscribe(( res ) => {
            const services: Services[] = res;

            this.runningServices = services.filter( services => services.status === "running" )
            this.runningServicesData = new MatTableDataSource( this.runningServices );

            this.stoppedServices = services.filter( services => services.status === "stopped" )
            this.stoppedServicesData = new MatTableDataSource( this.stoppedServices );

        }, ( error ) => {
            console.log( error );
        } );

        this.data.displayAvailibility( this.dashBoardData.serverId ).subscribe( res =>
            this.lineAvailibilityChart = new Chart( document.getElementById( 'availability-chart' ), {
                type: 'line',
                data: res,
                options: {
                    /* title: {
                         display: true,
                         position: 'bottom',
                         text: this.dashBoardData.serverId,
                         fontSize: 15,
                         fontColor: '#111'
                     },*/
                    legend: {
                        display: true,
                        position: 'bottom',

                        labels: {
                            fontColor: '#2F4F4F',
                            fontSize: 15
                        }
                    }
                }

            }
            )
        );

        this.cpuUsage();
        this.diskUsage();

        Observable.interval(1000 * 60).subscribe(x => {
            this.cpuUsage();
            this.diskUsage();
        });


        
    }


    cpuUsage (){
       this.data.displayCpuUsage( this.dashBoardData.serverId ).subscribe( res =>
            this.lineCpuUsageChart = new Chart( document.getElementById( 'cpuUsage-chart' ), {
                type: 'line',
                data: {
                    'labels':  res['labels'],
                    'datasets': [
                        {
                            'data': res['data'],
                            'label': 'Cpu Usage',
                            'borderColor': 'black',
                            'pointBorderColor': 'black',
                            'pointBackgroundColor': "#80b6f4",
                            'pointHoverBackgroundColor': "#80b6f4",
                            'pointHoverBorderColor': "#80b6f4",
                            'pointBorderWidth': 5,
                            'pointHoverRadius': 5,
                            'pointHoverBorderWidth': 1,
                            'pointRadius': 1,
                            'fill': true,
                            'borderWidth': 2
                        }]
                },
                options: {

                    /*title: {
                        display: true,
                        position: 'bottom',
                        text: 'CPU Usuage',
                        fontSize: 15,
                        fontColor: '#111'
                    },*/

                    legend: {
                        display: true,
                        position: 'bottom',
                        labels: {
                            fontColor: '#333',
                            fontSize: 15
                        }
                    }
                }

            }
            )
        );
   }


   diskUsage (){
       this.data.displayDiskUsage( this.dashBoardData.serverId ).subscribe( res =>
            this.pieDiskUsageChart = new Chart( document.getElementById( 'diskUsage-chart' ), {

                type: 'pie',
                data: {
                    'labels': ['Available', 'Used'],

                    'datasets': [
                        {
                            'data': [res['available'], res['used']],
                            'backgroundColor': [
                                "grey",
                                "#c00"
                            ],
                            'borderColor': [
                                '#E9DAC6',
                                '#CBCBCB'
                            ]
                        }]
                },
                options: {
                    responsive: true,
                    /*    title: {
                            display: true,
                            position: 'bottom',
                            text: 'Disk Usuage',
                            fontSize: 15,
                            fontColor: '#111'
                        },*/
                    legend: {
                        display: true,
                        position: 'bottom',
                        labels: {
                            fontColor: '#333',
                            fontSize: 15
                        }
                    }
                }

            }
            )
        );
   }

}
