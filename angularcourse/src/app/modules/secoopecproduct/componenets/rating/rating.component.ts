import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.scss']
})
export class RatingComponent implements OnInit {

  @Input()
  rating : number=2;
  maxrating : number=5;

  get fullstars():number{
    return Math.floor(this.rating)
  }
  get halfstar():boolean{
    return this.rating % 1 !==0;
  }
  get emtystar():number{
    return this.maxrating - Math.ceil(this.rating);
  }
  constructor() { }

  ngOnInit(): void {
  }

}
