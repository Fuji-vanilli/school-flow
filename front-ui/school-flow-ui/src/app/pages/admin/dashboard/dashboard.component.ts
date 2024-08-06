import { ChangeDetectionStrategy, Component, model, TemplateRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class DashboardComponent {
  selected = model<Date | null>(null);
}
