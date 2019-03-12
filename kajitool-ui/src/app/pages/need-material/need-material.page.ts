import { Component, OnInit } from '@angular/core';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-need-material',
  templateUrl: './need-material.page.html',
  styleUrls: ['./need-material.page.scss'],
})
export class NeedMaterialPage implements OnInit {

  constructor(public recipeService: RecipeService) { }

  ngOnInit() {
    this.recipeService.getNeedMaterial();
  }

  ionViewWillEnter() {
    this.recipeService.getNeedMaterial();
  }
}
