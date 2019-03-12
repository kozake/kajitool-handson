import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { HomePage } from './home.page';

const routes: Routes = [
  {
    path: '',
    component: HomePage,
    children: [
      {
        path: 'select-recipe',
        children: [
          {
            path: '',
            loadChildren: '../pages/select-recipe/select-recipe.module#SelectRecipePageModule'
          }
        ]
      },
      {
        path: 'need-material',
        children: [
          {
            path: '',
            loadChildren: '../pages/need-material/need-material.module#NeedMaterialPageModule'
          }
        ]
      },
      {
        path: '',
        redirectTo: '/home/select-recipe',
        pathMatch: 'full'
      }
    ]
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [HomePage]
})
export class HomePageModule {}
