import { Component } from '@angular/core';
import { DomainNavigation } from '../domain-navigation/domain-navigation';
import { InvestmentsPageComponent } from '../../../features/investments/investments-page-component/investments-page-component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-domain-nav-layout',
  imports: [
    RouterOutlet,
    DomainNavigation,
    InvestmentsPageComponent
  ],
  templateUrl: './domain-nav-layout.html',
  styleUrl: './domain-nav-layout.scss',
})
export class DomainNavigationLayout {}
