import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-vehicle-page',
  imports: [CommonModule,RouterLink,HttpClientModule,FormsModule],
  templateUrl: './add-vehicle-page.component.html',
  styleUrl: './add-vehicle-page.component.css'
})
export class AddVehiclePageComponent {

  public vehicle: any = {
    vehicleModel: '',
    vehicleYear: '',
    vehicleRegistrationNumber: '',
  };

  constructor(private http:HttpClient){}

  public onSubmit() {
    if (this.vehicle.vehicleModel && this.vehicle.vehicleYear && this.vehicle.vehicleRegistrationNumber) {
    this.http.post("http://localhost:8080/vehicle/add-vehicle",this.vehicle).subscribe(()=>{
      this.successAlert();
      this.resetForm();
   },
   (error) => {
     console.error('Error:', error);
   }
 );
}
}

  resetForm() {
    this.vehicle = {
      vehicleModel: '',
      vehicleYear: '',
      vehicleRegistrationNumber: '',
    };
  }

  public successAlert(){
    Swal.fire({
      title: "The vehicle Added Successfully?",
      icon: "success",
      background:"#fff",
    });
  }
  

}
