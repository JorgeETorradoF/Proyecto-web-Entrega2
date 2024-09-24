import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { logInComponent } from './app/logIn.component';

bootstrapApplication(logInComponent, appConfig)
  .catch((err) => console.error(err));
