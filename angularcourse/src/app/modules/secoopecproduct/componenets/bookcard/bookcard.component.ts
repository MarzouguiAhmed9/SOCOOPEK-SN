import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProdcutResponse} from "../../../../services/models/prodcut-response";

@Component({
  selector: 'app-bookcard',
  templateUrl: './bookcard.component.html',
  styleUrls: ['./bookcard.component.scss']
})
export class BookcardComponent implements OnInit {
  private _productcover: String | undefined

  constructor() {
  }

  private _product: ProdcutResponse = {};
  private _manage: boolean = false;
  get manage(): boolean {
    return this._manage;
  }
@Input()
  set manage(value: boolean) {
    this._manage = value;
  }

  get product(): ProdcutResponse {
    return this._product;
  }

  @Input()
  set product(value: ProdcutResponse) {
    this._product = value;
  }

  get productcover(): String | undefined {
    if (this._product.productimage) {
      return 'data:image/jpg;base64,' + this._product.productimage
    }

    return 'https://source.unsplash.com/user/c_v_r/1900x800';

  }

  set productcover(value: String | undefined) {
    this._productcover = value;
  }
@Output()
private details:EventEmitter<ProdcutResponse>=new EventEmitter<ProdcutResponse>()
  ngOnInit(): void {
  }

  onshowdetails() {
    this.details.emit(this._product)

  }

  onedit() {

  }

  onshare() {

  }

  onarchive() {

  }
}
