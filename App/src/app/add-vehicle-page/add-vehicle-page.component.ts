import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-add-vehicle-page',
  imports: [CommonModule,RouterLink,HttpClientModule,FormsModule],
  templateUrl: './add-vehicle-page.component.html',
  styleUrl: './add-vehicle-page.component.css'
})
export class AddVehiclePageComponent {
  vehicle = {
    title: '',
    category: '',
    manufacturer: '',
    price: 0,
    qty: 0
  };

  onSubmit(form: NgForm) {
    if (form.valid) {
      console.log('Vehicle Added:', this.vehicle);
      // You can also call a service here to send data to the backend.
      form.reset();
    } else {
      alert('Please fill in all fields.');
    }
  }
  

}
