import { Component } from '@angular/core';
import { DomainNavigation } from '../domain-navigation/domain-navigation';
import { InvestmentsPageComponent } from '../../../features/investments/investments-page-component/investments-page-component';

@Component({
  selector: 'app-domain-nav-layout',
  imports: [
    DomainNavigation,
    InvestmentsPageComponent
  ],
  templateUrl: './domain-nav-layout.html',
  styleUrl: './domain-nav-layout.scss',
})
export class DomainNavigationLayout {}
