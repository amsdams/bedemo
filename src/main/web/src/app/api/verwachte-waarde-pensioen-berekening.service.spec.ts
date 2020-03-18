import { TestBed } from '@angular/core/testing';

import { VerwachteWaardePensioenBerekeningService } from './verwachte-waarde-pensioen-berekening.service';

describe('VerwachteWaardePensioenBerekeningService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VerwachteWaardePensioenBerekeningService = TestBed.get(VerwachteWaardePensioenBerekeningService);
    expect(service).toBeTruthy();
  });
});
