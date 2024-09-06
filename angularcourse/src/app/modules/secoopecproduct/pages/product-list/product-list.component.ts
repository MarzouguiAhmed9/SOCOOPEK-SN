import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../../../services/services/product.service";
import {Router} from "@angular/router";
import {PageResponseProdcutResponse} from "../../../../services/models/page-response-prodcut-response";
import {Productrequest} from "../../../../services/models/productrequest";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {
   page: number=0;
   size: number=5;
   producresponse : PageResponseProdcutResponse={}
  ngOnInit(): void {
    this.findallproducts();
  }
constructor(
  private productservice: ProductService,
  private router : Router
) {
}

  private findallproducts() {
    this.productservice.finadallproduct({
      page:this.page,
      size:this.size
    }).subscribe({next:(product)=>{this.producresponse=product}})

  }
}
