import {DashboardService} from '../dashboard.service';
import {UserInGroup} from '../model/UserInGroup.model';
import {Component, OnInit, OnDestroy} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-searchassign',
  templateUrl: './searchassign.component.html',
  styleUrls: ['./searchassign.component.css']
})
export class SearchassignComponent implements OnInit, OnDestroy {
  query: string;
  searchResults: Array<UserInGroup>;
  sub: Subscription;
  private userInGroupList: UserInGroup[];

  constructor(private dashboardService: DashboardService, private route: ActivatedRoute, private _router: Router) {}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      if (params['term']) {
        this.query = decodeURIComponent(params['term']);
        this.search();
      }
    });
  }

  search(): void {
    if (this.query != null && this.query.trim != undefined) {
      this.dashboardService.search(this.query).subscribe(
        (data: any) => {this.searchResults = data;},
        error => console.log(error)
      );
    } else {
      this.searchResults = new Array<UserInGroup>();
    }

  }

  deleteByUserIdAndGroupName(userInGroup) {
    this.dashboardService.deleteByUserIdAndGroupName(userInGroup).subscribe((data) => {
      this.searchResults.splice(this.searchResults.indexOf(userInGroup), 1);
    }, (error) => {
      console.log(error);
    });
  }

  updateGroupName(userInGroup) {
    this.dashboardService.setterUserInGroup(userInGroup);
    this._router.navigate(['/edit-group-name']);

  }

  assignNewGroup() {
    let userInGroup = new UserInGroup();
    this.dashboardService.setterUserInGroup(userInGroup);
    this._router.navigate(['/edit-group-name']);

  }

  ngOnDestroy() {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }
}