import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {ProductListComponent} from "./pages/product-list/product-list.component";
import {ExchangeproductComponent} from "./pages/exchangeproduct/exchangeproduct.component";
import {ManageProductComponent} from "./pages/manage-product/manage-product/manage-product.component";

const routes: Routes = [
  {
path : "",
    component :MainComponent,
    children: [
      { path: '', component: ProductListComponent },
      { path: 'products', component: ProductListComponent },
      { path: 'exchangeproduct', component: ExchangeproductComponent },
      { path: 'manage', component: ManageProductComponent },
      { path: 'manage/:productid', component: ManageProductComponent },


    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SecoopecproductRoutingModule { }
