import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewVehiclePageComponent } from './view-vehicle-page.component';

describe('ViewVehiclePageComponent', () => {
  let component: ViewVehiclePageComponent;
  let fixture: ComponentFixture<ViewVehiclePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewVehiclePageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewVehiclePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
