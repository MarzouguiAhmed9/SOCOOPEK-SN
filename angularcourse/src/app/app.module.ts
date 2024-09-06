import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import { DetailsComponent } from './details/details.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ActivateAccountComponent } from './pages/activate-account/activate-account.component';
import {CodeInputModule} from "angular-code-input";
import {HttpTokenInterceptor} from "./services/interceptor/http-token.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    DetailsComponent,
    LoginComponent,
    RegisterComponent,
    ActivateAccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CodeInputModule
  ],
  providers: [HttpClient,
    {provide:HTTP_INTERCEPTORS,
    useClass:HttpTokenInterceptor,
    multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
