import { Injectable } from '@angular/core';
import { Account, AccountResourceService } from '@kajitool/kajitool-api';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  account: Account;
  isLogined = false;

  constructor(private accountResource: AccountResourceService) { }

  async init() {
    try {
      this.account = await this.accountResource.get().toPromise();
      this.isLogined = true;
      console.log(`${this.account.name} login.`);
    } catch (e) {
      console.log('no login.');
    }
  }
}
