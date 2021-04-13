import { Component, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';
import { DataService } from '../data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private dataService: DataService) {}

  usersData: any;
  friendsData: any;
  selectedId = new Set();
  errorFlag: boolean;

  ngOnInit() {
    this.errorFlag = false;
    this.dataService.getUserList().subscribe(
      (res) => {
        this.usersData = res;
      },
      (err) => {
        this.errorFlag = true;
      }
    );
  }

  getUserFriendsData(id: any) {
    if (this.selectedId.has(id)) {
      this.selectedId.delete(id);
    } else {
      this.selectedId.add(id);

      forkJoin([
        this.dataService.getUserFriends(id),
        this.dataService.getUserFriendSuggestion(id),
      ]).subscribe(
        (res) => {
          this.usersData = this.usersData.map((obj) => {
            if (obj.id === id) {
              obj.friendsDetails = res[0];
              obj.friendsSuggetion = res[1];
            }
            return obj;
          });
        },
        (err) => (this.errorFlag = true)
      );
    }
  }
}
