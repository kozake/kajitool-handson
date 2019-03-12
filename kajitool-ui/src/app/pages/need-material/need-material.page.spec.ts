import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NeedMaterialPage } from './need-material.page';

describe('NeedMaterialPage', () => {
  let component: NeedMaterialPage;
  let fixture: ComponentFixture<NeedMaterialPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NeedMaterialPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NeedMaterialPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
