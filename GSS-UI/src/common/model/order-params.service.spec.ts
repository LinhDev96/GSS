/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { OrderParamsService } from './order-params.service';

describe('Service: OrderParams', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OrderParamsService]
    });
  });

  it('should ...', inject([OrderParamsService], (service: OrderParamsService) => {
    expect(service).toBeTruthy();
  }));
});
