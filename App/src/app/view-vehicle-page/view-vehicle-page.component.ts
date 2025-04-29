import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-vehicle-page',
  imports: [RouterLink,FormsModule],
  templateUrl: './view-vehicle-page.component.html',
  styleUrl: './view-vehicle-page.component.css'
})
export class ViewVehiclePageComponent {

}
