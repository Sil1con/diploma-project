import { provideRouter, Routes } from '@angular/router';
import { MainSelection } from './components/main-selection/main-selection';
import { DomainNavigationLayout } from './layout/navigation-layout/domain-nav-layout/domain-nav-layout';
import { ApplicationConfig } from '@angular/core';
import { InvestmentsPageComponent } from './features/investments/investments-page-component/investments-page-component';
import { IncomePageComponenet } from './features/income/income-page-component/income-page-component';
import { ExpendituresPageComponent } from './features/expenditures/expenditures-page-component/expenditures-page-component';

export const routes: Routes = [
  {
    path: 'menu',
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
      },
      {
        path: 'expenditures',
        component: ExpendituresPageComponent
      }
    ]
  },
  {
    path: '**',
    redirectTo: 'menu'
  }
];

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes)
  ]
};