import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JaarlijksePremieStortingBerekeningListComponent } from './jaarlijkse-premie-storting-berekening-list.component';

describe('JaarlijksePremieStortingBerekeningListComponent', () => {
  let component: JaarlijksePremieStortingBerekeningListComponent;
  let fixture: ComponentFixture<JaarlijksePremieStortingBerekeningListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JaarlijksePremieStortingBerekeningListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JaarlijksePremieStortingBerekeningListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
