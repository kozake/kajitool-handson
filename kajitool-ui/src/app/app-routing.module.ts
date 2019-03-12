import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', loadChildren: './home/home.module#HomePageModule' },
  { path: 'select-recipe', loadChildren: './pages/select-recipe/select-recipe.module#SelectRecipePageModule' },
  { path: 'need-material', loadChildren: './pages/need-material/need-material.module#NeedMaterialPageModule' },
  { path: 'edit-recipe', loadChildren: './pages/edit-recipe/edit-recipe.module#EditRecipePageModule' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
