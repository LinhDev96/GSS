import { Item } from './../../model/item';
import { CoreService, orderParams } from './../../service/core.service';
// import { DetailDialogComponent } from './../../../dialog/product-detail/detail-dialog.component';
import { Component, OnInit } from '@angular/core';
// import { CoreService } from '../../service/core.service';
import { MatDialog } from '@angular/material/dialog';
import { RouterStateService } from '../../service/router-state.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailDialogComponent } from '../../../app/dialog/product-detail/detail-dialog.component';
import { CommonService } from 'src/common/service/common.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-content-home',
  templateUrl: './content-home.component.html',
  styleUrls: ['./content-home.component.css']
})
export class ContentHomeComponent implements OnInit {
  isOpen = false;
  items :any;
  total = 0;
  page = 0;
  pageSize = 10;
  pageSizeOptions: number[] = [10, 25, 100];
  isSpecificComponentActive:boolean = false;
  filter: any;
  timeFilter = '';
  nameFilter = '';
  priceFilter = '';
  bestsellerFilter:any;
  category = '';
  isDesc = true;

  alphabetOrder : string = '';

  orderParams2 : Object[]= [];
  keyValueList: { [key: string]: any }[] = [];
  private eventSubscription!: Subscription;
  pagingParams = {
    sort: "name,asc",
    pageSize: this.pageSize,
    page:this.page,
  };
  categories = [
    {
      name: 'Vehicle',
      isOpen: false,
      subcategories: ['Car (Toyota, Hyundai)', 'Plane', 'Tank']
    },
    {
      name: 'Drug',
      isOpen: false,
      subcategories: ['Painkiller', 'Weed']
    }
  ];
  constructor(
    public dialog: MatDialog,
    private apiService : CoreService,
    private route: ActivatedRoute,
    private router: Router,
    private commonService : CommonService,
  ) { }

  ngOnInit() {
    this.router.events.subscribe(() => {
      const currentRoute = this.route.snapshot.firstChild;
      this.isSpecificComponentActive = (currentRoute && currentRoute.routeConfig?.path === 'cart') as boolean;
  ``})
  this.initFilter();
  this.getProductByFilter(this.filter);

  this.eventSubscription = this.commonService.data$.subscribe(data => {
    if (data) {
      // console.log("receive :" + this.receivedData);
      this.filter[0] = data;
      this.getProductByFilter(this.filter);
    }
  });
  }

  showTimeDropdown = false;
  showPriceDropdown = false;

  initFilter(){
    this.filter = [
      this.category ?? '',
      this.timeFilter ?? '',
      this.nameFilter ?? '',
      this.priceFilter ?? '',
      this.bestsellerFilter ?? '',
    ];
  }

  initOrder(param : string, order : string){
      if(this.keyValueList.some(obj => obj.hasOwnProperty(param))){//trung
        const item = this.keyValueList.find(obj => obj.hasOwnProperty(param));
          if (item) {
            this.keyValueList.splice(this.keyValueList.indexOf(item), 1);
            console.log("delete: "+this.keyValueList);
            this.keyValueList.unshift({[param]: order});
          }
      }else{//ko trung
        this.keyValueList.unshift({[param]: order});
      }
    console.log(this.keyValueList);
    this.pagingParams.sort=this.keyValueList.map(obj => {
      const key = Object.keys(obj)[0];
      const value = obj[key];
      return `${key},${value}`;
    })
    .join(';');
  }

  toggleTimeDropdown() {
    this.showTimeDropdown = !this.showTimeDropdown;
  }

  togglePriceDropdown() {
    this.showPriceDropdown = !this.showPriceDropdown;
  }

  setTimeFilter(data:string){
    this.initOrder("createdAt",data);

    this.initFilter();
    this.filter[1] = data;
    this.initFilter();
    this.getProductByFilter(this.filter);
  }
  setNameOrder(){

    this.isDesc = !this.isDesc;
    if(this.isDesc){
      this.initOrder("name",'desc');
    }else{
      this.initOrder("name",'asc');
    }
    this.initFilter();
    this.getProductByFilter(this.filter);
  }

  toggleDropdown() {
    this.isOpen = !this.isOpen;
  }

  toggleSubmenu(category: any) {
    category.isOpen = !category.isOpen;
  }

  getAllProducts(){
    this.apiService.getData().subscribe({
      next: (response: any) => {
        this.items = response;
        this.total = this.items.length;
        console.log(this.items);
      },
      error: (error: any) => {
        console.error('Có lỗi xảy ra! load data  ', error);
      },
      complete: () => {
        console.log('Hoàn thành!');
      }
    })
  }
  getProductByFilter(filters : string[]){
    console.log(this.pagingParams.sort);
    this.apiService.getProductByFilter(filters,this.pagingParams).subscribe({
      next: (response: any) => {
        this.items = response?.content;
        this.total = response?.totalElements;
      },
      error: (error: any) => {
        console.error('Có lỗi xảy ra!  ', error);
      },
      complete: () => {

      }
    });
  }

  navigate(event: any) {
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.pagingParams.pageSize = this.pageSize;
    this.pagingParams.page = this.page;
    this.initFilter();
    this.getProductByFilter(this.filter);
  }

  openDetailDialog(Item: any){
    const dialogRef = this.dialog.open(DetailDialogComponent, {
      data:{
        itemId : Item.id,
        productName : Item.name,
        unitPrice : Item.unitPrice,
      }
    });
  }

  setPriceFilter(data:string){
    this.initFilter();
    this.initOrder("unitPrice",data);
    this.getProductByFilter(this.filter);
  }

}
