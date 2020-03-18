import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerwachteWaardePensioenBerekeningListComponent } from './verwachte-waarde-pensioen-berekening-list.component';

describe('VerwachteWaardePensioenBerekeningListComponent', () => {
  let component: VerwachteWaardePensioenBerekeningListComponent;
  let fixture: ComponentFixture<VerwachteWaardePensioenBerekeningListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [VerwachteWaardePensioenBerekeningListComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerwachteWaardePensioenBerekeningListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
