import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-activateaccount',
  templateUrl: './activateaccount.component.html',
  styleUrls: ['./activateaccount.component.scss']
})
export class ActivateaccountComponent implements OnInit {
message: string ="";
isOkay : boolean=true;
submitted:boolean=false;
  constructor(private router:Router,private authservice:AuthenticationService) { }

  ngOnInit(): void {
  }

  onCodeCompleted(token: string) {

  }

  redirectToLogin() {

  }
}
