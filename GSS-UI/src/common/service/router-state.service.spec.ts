/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { RouterStateService } from './router-state.service';

describe('Service: RouterState', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RouterStateService]
    });
  });

  it('should ...', inject([RouterStateService], (service: RouterStateService) => {
    expect(service).toBeTruthy();
  }));
});
