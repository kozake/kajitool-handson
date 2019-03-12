import { MaterialService } from './../../services/material.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { LoadingController } from '@ionic/angular';
import { Recipe, Material } from '@kajitool/kajitool-api';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-edit-recipe',
  templateUrl: './edit-recipe.page.html',
  styleUrls: ['./edit-recipe.page.scss'],
})
export class EditRecipePage implements OnInit {
  id: string;
  isNew: boolean;
  recipe: Recipe;
  materials: Material[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private loadingController: LoadingController,
    public materialService: MaterialService,
    public recipeService: RecipeService
  ) {
    this.recipe = this.recipeService.newRecipe();
  }

  async ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.isNew = this.id === 'new';
    if (!this.isNew) {
      this.recipe = await this.recipeService.get(Number(this.id));
    }
    this.materials = this.materialService.getMateials();
  }

  onAddMaterial() {
    this.recipe.recipeDetails.push({
      quantity: 1
    });
  }

  onRemoveMaterial(i: number) {
    this.recipe.recipeDetails.splice(i, 1);
  }

  canRegist(recipeForm: NgForm): boolean {
    return !recipeForm.invalid && this.recipe.recipeDetails.length > 0;
  }

  async onCreate() {
    const loading = await this.loadingController.create({
      message: 'processing...'
    });
    await loading.present();

    try {
      await this.recipeService.create(this.recipe);
      await this.recipeService.init();
    } finally {
      await loading.dismiss();
    }
    this.router.navigate(['/home']);
  }

  async onSave() {
    const loading = await this.loadingController.create({
      message: 'processing...'
    });
    await loading.present();

    try {
      await this.recipeService.save(this.recipe);
      await this.recipeService.init();
    } finally {
      await loading.dismiss();
    }
    this.router.navigate(['/home']);
  }

  async onRemove() {
    const loading = await this.loadingController.create({
      message: 'processing...'
    });
    await loading.present();

    try {
      await this.recipeService.remove(this.recipe);
      await this.recipeService.init();
    } finally {
      await loading.dismiss();
    }
    this.router.navigate(['/home']);
  }
}
