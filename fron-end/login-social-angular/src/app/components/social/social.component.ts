import { SocialService } from './../../services/social.service';
import { Component, OnInit } from '@angular/core';
import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthService, SocialUser } from "angularx-social-login";

@Component({
  selector: 'app-social',
  templateUrl: './social.component.html',
  styleUrls: ['./social.component.css']
})
export class SocialComponent implements OnInit {

  user: SocialUser;
  isLogin: boolean;

  constructor(private authService: SocialAuthService, private socialService: SocialService) { }



  ngOnInit(): void {
    this.authService.authState.subscribe(
      data =>{
        this.isLogin = (data != null);
      }
    )

  }




  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(
      data =>{
        this.socialService.loginWithGoogle(data.idToken).subscribe(
          res =>{
            console.log(res);
          }
       )
      }
    );


  }

  signInWithFB(): void {
    this.authService.signIn(FacebookLoginProvider.PROVIDER_ID).then(
      data =>{
        this.socialService.loginWithFacebook(data.authToken).subscribe(
          res =>{
            console.log(res);
          }
        )
      }
    );
  }

  signOut(): void {
    this.authService.signOut();
  }




}
