import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/services/account.service';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-select-recipe',
  templateUrl: './select-recipe.page.html',
  styleUrls: ['./select-recipe.page.scss'],
})
export class SelectRecipePage implements OnInit {

  constructor(
    public accountService: AccountService,
    public recipeService: RecipeService
  ) { }

  ngOnInit() {
  }

}
