import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './layout/header/header-component';
import { FooterComponent } from './layout/footer/footer-component';
import { MainSelection } from './components/main-selection/main-selection';
import { DomainNavigationLayout } from './layout/navigation-layout/domain-nav-layout/domain-nav-layout';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet, 
    HeaderComponent,
    MainSelection, 
    FooterComponent,
    DomainNavigationLayout
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('diploma-project');
}
