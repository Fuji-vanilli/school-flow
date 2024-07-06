import { Component, inject } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  kcService= inject(KeycloakService);

  logout() {
    this.kcService.logout(window.location.origin);
  }
}
