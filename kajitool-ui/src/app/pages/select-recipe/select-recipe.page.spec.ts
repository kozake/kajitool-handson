import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectRecipePage } from './select-recipe.page';

describe('SelectRecipePage', () => {
  let component: SelectRecipePage;
  let fixture: ComponentFixture<SelectRecipePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectRecipePage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectRecipePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
