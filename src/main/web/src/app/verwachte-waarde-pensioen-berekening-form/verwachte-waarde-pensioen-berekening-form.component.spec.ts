import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerwachteWaardePensioenBerekeningFormComponent } from './verwachte-waarde-pensioen-berekening-form.component';

describe('VerwachteWaardePensioenBerekeningFormComponent', () => {
  let component: VerwachteWaardePensioenBerekeningFormComponent;
  let fixture: ComponentFixture<VerwachteWaardePensioenBerekeningFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [VerwachteWaardePensioenBerekeningFormComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerwachteWaardePensioenBerekeningFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
