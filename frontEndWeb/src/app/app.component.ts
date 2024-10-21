import { Component } from '@angular/core';
import { NavbarComponent } from './Landing-Page/navbar/navbar.component';
import { FooterComponent } from './Landing-Page/footer/footer.component';
import { AboutUsComponent } from './Landing-Page/about-us/about-us.component';

@Component({
  selector: 'app-root', 
  templateUrl: './app.component.html',

})
export class AppComponent {
  title = 'FrontEndWeb';  // Puedes cambiar el título según tu proyecto
}
