import {DashboardService} from '../dashboard.service';
import {UserInGroup} from '../model/UserInGroup.model';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-userassignedit',
  templateUrl: './userassignedit.component.html',
  styleUrls: ['./userassignedit.component.css']
})
export class UserassigneditComponent implements OnInit {

  private userInGroup: UserInGroup;

  constructor(private dashboardServer: DashboardService, private _rotuer: Router) {}

  ngOnInit() {

    this.userInGroup = this.dashboardServer.getterUserInGroup();
  }

  processForm() {
    if (this.userInGroup.userId === undefined) {
      this.dashboardServer.createUserInGroup(this.userInGroup).subscribe((response) => {
        console.log(response);
        this._rotuer.navigate(['/assign-server']);
      }, (error) => {
        console.log(error);
      });
    } else {
      this.dashboardServer.updateUserInGroup(this.userInGroup).subscribe((response) => {
        console.log(response);
        this._rotuer.navigate(['/assign-server']);
      }, (error) => {
        console.log(error);
      });
    }
  }

}
