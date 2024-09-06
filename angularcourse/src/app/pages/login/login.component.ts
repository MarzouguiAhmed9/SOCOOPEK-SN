import { Component, OnInit } from '@angular/core';
import {AuthenticateRequest} from "../../services/models/authenticate-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";
import {TokenService} from "../../services/token/token.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  authRequest : AuthenticateRequest={email :"", password:""};
  errorMessage : Array<string> = [] ;
  test : Number=0;
  constructor(
   private router : Router,
    private authservice : AuthenticationService,
   private  tokenservice : TokenService
  ) { }

  ngOnInit(): void {
  }

  login() {
    this.errorMessage = [];
    this.authservice.authenticate({ body: this.authRequest }).subscribe({
      next: (res) => {
        this.tokenservice.token=res.token as string;//save token
        this.router.navigate(['products']);
      },
      error: (err) => {
        console.log(err);
        // Check for validation errors
        if (err.error.validationErrors) {
          this.errorMessage = err.error.validationErrors;
        } else {
          // Handle general errors, including the BadCredentialsException
          this.errorMessage.push(err.error.error || 'An unknown error occurred');
        }
      }
    });
  }



  register() {
    this.router.navigate(['register'])
  }
}
