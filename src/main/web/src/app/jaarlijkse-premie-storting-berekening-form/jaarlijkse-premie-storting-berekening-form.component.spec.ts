import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JaarlijksePremieStortingBerekeningFormComponent } from './jaarlijkse-premie-storting-berekening-form.component';

describe('JaarlijksePremieStortingBerekeningFormComponent', () => {
  let component: JaarlijksePremieStortingBerekeningFormComponent;
  let fixture: ComponentFixture<JaarlijksePremieStortingBerekeningFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JaarlijksePremieStortingBerekeningFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JaarlijksePremieStortingBerekeningFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
