import { Component } from '@angular/core';
import { DomainNavigation } from '../domain-navigation/domain-navigation';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-domain-nav-layout',
  imports: [
    RouterOutlet,
    DomainNavigation
  ],
  templateUrl: './domain-nav-layout.html',
  styleUrl: './domain-nav-layout.scss',
})
export class DomainNavigationLayout {}
