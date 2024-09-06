import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.scss']
})
export class ActivateAccountComponent implements OnInit {
message:string="";
  isOkay : boolean=true;
  submitted:boolean=false;
  constructor(
    private router:Router,
    private authservice:AuthenticationService
  ) {

  }

  ngOnInit(): void {
  }

  onCodeCompleted(token: string) {
    this.confirmaccount(token);

  }

  redirectToLogin() {
    this.router.navigate(['login'])
  }

  private confirmaccount(token: any) {
this.authservice.confirm({token}).subscribe({next:()=>
  {this.message="your account hase been succefly activated\n now you can log in"
    this.submitted=true;},
  error:()=>{this.message="expired token"
this.submitted=true;
this.isOkay=false}
}
  )

  }


}
