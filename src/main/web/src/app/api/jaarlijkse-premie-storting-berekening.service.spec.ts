import { TestBed } from '@angular/core/testing';

import { JaarlijksePremieStortingBerekeningService } from './jaarlijkse-premie-storting-berekening.service';

describe('JaarlijksePremieStortingBerekeningService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: JaarlijksePremieStortingBerekeningService = TestBed.get(JaarlijksePremieStortingBerekeningService);
    expect(service).toBeTruthy();
  });
});
