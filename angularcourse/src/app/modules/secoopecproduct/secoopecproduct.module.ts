import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SecoopecproductRoutingModule } from './secoopecproduct-routing.module';
import { MainComponent } from './pages/main/main.component';
import { MenuComponent } from './componenets/menu/menu.component';
import { ProductListComponent } from './pages/product-list/product-list.component';
import { ExchangeproductComponent } from './pages/exchangeproduct/exchangeproduct.component';
import { ManageProductComponent } from './pages/manage-product/manage-product/manage-product.component';
import {FormsModule} from "@angular/forms";
import { BookcardComponent } from './componenets/bookcard/bookcard.component';
import { RatingComponent } from './componenets/rating/rating.component';


@NgModule({
  declarations: [
    MainComponent,
    MenuComponent,
    ProductListComponent,
    ExchangeproductComponent,
    ManageProductComponent,
    BookcardComponent,
    RatingComponent
  ],
  imports: [
    CommonModule,
    SecoopecproductRoutingModule,
    FormsModule
  ]
})
export class SecoopecproductModule { }
