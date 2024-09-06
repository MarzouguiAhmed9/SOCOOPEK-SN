import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})
export class DetailsComponent implements OnInit {
 @Input()
 detaille : any ={} ;

 @Input()
 index : number=0
  constructor() { }
@Output()
delete : EventEmitter<any>=new EventEmitter<any>()
  ngOnInit(): void {
  }

  ondelete() {
   this.delete.emit(this.index);

  }
}
