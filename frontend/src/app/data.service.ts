import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class DataService {
  constructor(private http: HttpClient) {}

  baseUrl = 'https://spring-userapp.herokuapp.com';

  getUserList() {
    return this.http.get(`${this.baseUrl}/api/users`);
  }

  getUserFriends(id: any) {
    return this.http.get(`${this.baseUrl}/api/user/friends/${id}`);
  }

  getUserFriendSuggestion(id: any) {
    return this.http.get(`${this.baseUrl}/api/user/suggestions/${id}`);
  }
}
