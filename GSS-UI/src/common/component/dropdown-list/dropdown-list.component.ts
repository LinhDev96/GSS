import { CommonService } from './../../service/common.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MenuItem } from 'src/common/model/menuItem';

@Component({
  selector: 'app-dropdown-list',
  templateUrl: './dropdown-list.component.html',
  styleUrls: ['./dropdown-list.component.css'],
})
export class DropdownListComponent implements OnInit {
  @Input() item: MenuItem;
  @Input() path: string[] = [];
  @Output() itemClicked = new EventEmitter<string>();
  isOpen: boolean = false;

  constructor(
    private commonService : CommonService,
  ) {}

  toggleDropdown(item: MenuItem, event: Event) {
    item.isOpen = !item.isOpen; // Đảo ngược trạng thái mở/đóng
    console.log(item.name + ': ' + item.isOpen);
    event.stopPropagation();
    this.isOpen = !this.isOpen;
    const fullPath = this.path.concat(item.name).join('/');
    this.itemClicked.emit(fullPath);
    console.log('Full path:', fullPath);
    this.commonService.sendData(fullPath.replace("Categories/", ""));

    if (!this.isOpen) {
      this.closeOtherSubItems(this.item);
    }
  }

  closeOtherSubItems(item: any) {
    if (item.children) {
      item.children.forEach((child: any) => {
        child.isOpen = false;
        this.closeOtherSubItems(child);
      });
    }
  }
  closeAll() {
    this.isOpen = false;
    if (this.item.children) {
      this.item.children.forEach((child: any) => {
        child.isOpen = false;
        this.closeOtherSubItems(child);
      });
    }
  }

  ngOnInit() {}
}
