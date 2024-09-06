import { Component, OnInit } from '@angular/core';
import {RegistrationRequest} from "../../services/models/registration-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerRequest : RegistrationRequest={email: "",firstname:"", lastname : "", password:""}
  errorMsg : Array<string>=[];
  constructor(
   private router:Router,
    private authservice:AuthenticationService,
  ) { }

  ngOnInit(): void {
  }


  register() {
    this.errorMsg = [];
    this.authservice.register({ body: this.registerRequest }).subscribe({
      next: () => {
        this.router.navigate(["activate-account"]);
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
