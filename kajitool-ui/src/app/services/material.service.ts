import { Injectable } from '@angular/core';
import { Material, MaterialResourceService } from '@kajitool/kajitool-api';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {
  mateials: Material[] = [];

  constructor(
    private materialResource: MaterialResourceService
  ) { }

  async init(): Promise<Material[]> {
    this.mateials = await this.materialResource.getAll().toPromise();
    return this.mateials;
  }

  getMateial(id: number): Material {
    return this.mateials.find(m => m.id === id);
  }

  getMateials(): Material[] {
    return this.mateials;
  }
}
