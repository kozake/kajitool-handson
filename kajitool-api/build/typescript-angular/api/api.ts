export * from './accountResource.service';
import { AccountResourceService } from './accountResource.service';
export * from './materialResource.service';
import { MaterialResourceService } from './materialResource.service';
export * from './recipeListViewResource.service';
import { RecipeListViewResourceService } from './recipeListViewResource.service';
export * from './recipeResource.service';
import { RecipeResourceService } from './recipeResource.service';
export const APIS = [AccountResourceService, MaterialResourceService, RecipeListViewResourceService, RecipeResourceService];
