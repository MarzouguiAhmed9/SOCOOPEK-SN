import { Component, OnInit } from '@angular/core';
import {RegistrationRequest} from "../../services/models/registration-request";
import {AuthenticationService} from "../../services/services/authentication.service";
import {Router} from "@angular/router";
import {error} from "@angular/compiler/src/util";

@Component({
  selector: 'app-regsiter',
  templateUrl: './regsiter.component.html',
  styleUrls: ['./regsiter.component.scss']
})
export class RegsiterComponent implements OnInit {

  constructor(  private  authservice : AuthenticationService,
                private router : Router
  ) { }
  registerRequest : RegistrationRequest={email: "",firstname:"", lastname : "", password:""}
  errorMsg : Array<string>=[];

  ngOnInit(): void {
  }

  register() {
    this.errorMsg = [];
    this.authservice.register({ body: this.registerRequest }).subscribe({
      next: () => {
        this.router.navigate(["activateaccount"]);
      },
      error: (err) => {
        // Improved error handling
        this.errorMsg = err.error.validationErrors ;
      }
    });
  }

  login() {
    this.router.navigate(['login']);
  }
}
