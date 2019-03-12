import { Injectable } from '@angular/core';
import { RecipeListView, RecipeListViewResourceService } from '@kajitool/kajitool-api';

export interface RecipeListViewModel extends RecipeListView {
  selected: boolean;
  orderQuantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  recipeList: RecipeListViewModel[] = [];

  constructor(
    private recipeListViewResource: RecipeListViewResourceService,
  ) { }

  async init(): Promise<RecipeListViewModel[]> {
    const list: RecipeListView[] = await this.recipeListViewResource.getAll().toPromise();
    this.recipeList = [];
    list.forEach(recipe => {
      this.recipeList.push({selected: false, orderQuantity: 1, ...recipe});
    });
    return this.recipeList;
  }
}
