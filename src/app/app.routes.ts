import { provideRouter, Routes } from '@angular/router';
import { MainSelection } from './components/main-selection/main-selection';
import { DomainNavigationLayout } from './layout/navigation-layout/domain-nav-layout/domain-nav-layout';
import { ApplicationConfig } from '@angular/core';
import { InvestmentsPageComponent } from './features/investments/investments-page-component/investments-page-component';
import { IncomePageComponenet } from './features/income/income-page-component/income-page-component';

export const routes: Routes = [
  {
    path: '',
    title: 'Devo',
    component: MainSelection
  },
  {
    path: 'dashboard',
    component: DomainNavigationLayout,
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'investments'
      },
      {
        path: 'investments',
        component: InvestmentsPageComponent
      },
      {
        path: 'income',
        component: IncomePageComponenet
      }
    ]
  },
  {
    path: '**',
    redirectTo: ''
  }
];

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes)
  ]
};