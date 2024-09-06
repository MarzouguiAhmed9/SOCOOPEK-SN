import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExchangeproductComponent } from './exchangeproduct.component';

describe('ExchangeproductComponent', () => {
  let component: ExchangeproductComponent;
  let fixture: ComponentFixture<ExchangeproductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExchangeproductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExchangeproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
