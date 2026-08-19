import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './layout/header/header-component';
import { FooterComponent } from './layout/footer/footer-component';
import { MainSelection } from './components/main-selection/main-selection';
import { DomainNavigationLayout } from './layout/navigation-layout/domain-nav-layout/domain-nav-layout';
import { CommodityInvestmentForm } from './features/investments/forms/commodity-investment-form/commodity-investment-form';
import { BondInvestmentForm } from './features/investments/forms/bond-investment-form/bond-investment-form';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet, 
    HeaderComponent,
    FooterComponent
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('diploma-project');
}
