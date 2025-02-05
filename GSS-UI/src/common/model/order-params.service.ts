import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrderParamsService {

constructor() { }

private orderParams = {
  name: 'asc',
};

getOrderParams() {
  return this.orderParams;
}

addProperty(key: string, value: string) {
  this.orderParams[key ? 'name' : 'name'] = value;
}

updateProperty(key: string, value: string) {
  if (this.orderParams.hasOwnProperty(key)) {
    this.orderParams[`${key}`] = value;
  } else {
    console.log('Property does not exist.');
  }
}

deleteProperty(key: string) {
  if (this.orderParams.hasOwnProperty(key)) {
    delete this.orderParams[key];
  } else {
    console.log('Property does not exist.');
  }
}
}
