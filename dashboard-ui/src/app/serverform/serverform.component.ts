import {Component, OnInit} from '@angular/core';
import {ServerGroup} from '../model/ServerGroup.model';
import {Router} from '@angular/router';
import {DashboardService} from '../dashboard.service';

@Component({
  selector: 'app-serverform',
  templateUrl: './serverform.component.html',
  styleUrls: ['./serverform.component.css']
})
export class ServerformComponent implements OnInit {

  private serverGroup: ServerGroup;

  constructor(private dashboardServer: DashboardService, private _rotuer: Router) {}

  ngOnInit() {

    this.serverGroup = this.dashboardServer.getterServerGroup();
  }

  processForm() {
    if (this.serverGroup.serverId === undefined) {
      this.dashboardServer.createServer(this.serverGroup).subscribe((response) => {
        console.log(response);
        this._rotuer.navigate(['/']); 
      }, (error) => {
        console.log(error);
      });
    } else {
      this.dashboardServer.updateServer(this.serverGroup).subscribe((response) => {
        console.log(response);
        this._rotuer.navigate(['/']);
      }, (error) => {
        console.log(error);
      });
    }
  }

}
