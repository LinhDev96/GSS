import { CommonService } from './../../service/common.service';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,

  ) { }

  ngOnInit() {
    // this.images=this.data?.images;
  }

  images = [
    'https://via.placeholder.com/800x400?text=Slide+1',
    'https://via.placeholder.com/800x400?text=Slide+2',
    'https://via.placeholder.com/800x400?text=Slide+3'
  ];
  currentIndex = 0;

  getTransform() {
    return `translateX(-${this.currentIndex * 100}%)`;
  }

  prevSlide() {
    this.currentIndex = (this.currentIndex > 0) ? this.currentIndex - 1 : this.images.length - 1;
  }

  nextSlide() {
    this.currentIndex = (this.currentIndex < this.images.length - 1) ? this.currentIndex + 1 : 0;
  }

}
