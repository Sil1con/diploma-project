import { Component } from '@angular/core';
import { DomainNavigation } from '../domain-navigation/domain-navigation';

@Component({
  selector: 'app-domain-nav-layout',
  imports: [
    DomainNavigation
  ],
  templateUrl: './domain-nav-layout.html',
  styleUrl: './domain-nav-layout.scss',
})
export class DomainNavigationLayout {}
