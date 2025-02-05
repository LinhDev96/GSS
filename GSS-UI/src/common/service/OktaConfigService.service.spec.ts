/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { OktaConfigServiceService } from './OktaConfigService.service';

describe('Service: OktaConfigService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OktaConfigServiceService]
    });
  });

  it('should ...', inject([OktaConfigServiceService], (service: OktaConfigServiceService) => {
    expect(service).toBeTruthy();
  }));
});
