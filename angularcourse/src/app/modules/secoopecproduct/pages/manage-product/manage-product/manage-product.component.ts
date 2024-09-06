import { Component, OnInit } from '@angular/core';
import {Productrequest} from "../../../../../services/models/productrequest";
import {ProductService} from "../../../../../services/services/product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-manage-product',
  templateUrl: './manage-product.component.html',
  styleUrls: ['./manage-product.component.scss']
})
export class ManageProductComponent implements OnInit {
errorMsg: Array<string>=[];
  selectedPicture: string | undefined;
  selectedproductcover: any;
  productrequest : Productrequest={description: "", identifiant: 0, price: 0, producttitle: ""}

  constructor(private productservice: ProductService,
              private router:Router) { }

  ngOnInit(): void {
  }

  onFileSelected(event: any) {
    this.selectedproductcover=event.target.files[0]
    console.log(this.selectedproductcover)
    if(this.selectedproductcover){
      const reader:FileReader=new FileReader();

      reader.onload= ()=>{
        this.selectedPicture=reader.result as string
      }
      reader.readAsDataURL(this.selectedproductcover)

    }

  }



  saveproduct() {
    this.productservice.savebook({body
    :this.productrequest}).subscribe ({next:(pid:number)=>{this.productservice.uploadproductimage
      ({"product-id": pid,body:{file:this.selectedproductcover}})
        .subscribe({next:()=>{this.router.navigate(['products'])}})},
    error:(err)=>{this.errorMsg=err.error.validationErrors}}

    )

  }
}
