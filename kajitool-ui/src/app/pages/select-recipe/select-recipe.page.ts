import { Component, OnInit } from '@angular/core';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-select-recipe',
  templateUrl: './select-recipe.page.html',
  styleUrls: ['./select-recipe.page.scss'],
})
export class SelectRecipePage implements OnInit {

  constructor(
    public recipeService: RecipeService
  ) { }

  ngOnInit() {
  }

}
