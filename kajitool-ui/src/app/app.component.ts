import { Component } from '@angular/core';

import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { AccountService } from './services/account.service';
import { MaterialService } from './services/material.service';
import { RecipeService } from './services/recipe.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent {
  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    public accountService: AccountService,
    public materialService: MaterialService,
    public recipeService: RecipeService,
  ) {
    this.initializeApp();
  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.splashScreen.hide();

      this.accountService.init();
      this.materialService.init();
      this.recipeService.init();
    });
  }

  goLogin() {
    location.href = 'oauth2/authorization/github';
  }
}
