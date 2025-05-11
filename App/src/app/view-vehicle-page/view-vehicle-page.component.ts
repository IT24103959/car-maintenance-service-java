import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-vehicle-page',
  imports: [FormsModule,CommonModule,HttpClientModule],
  templateUrl: './view-vehicle-page.component.html',
  styleUrl: './view-vehicle-page.component.css'
})
export class ViewVehiclePageComponent {
  public searchQuery: string = '';

  public vehicleList:any = [];

  public vehicleTemp:any = {}

  constructor(private http: HttpClient) {
    this.loadTable();
  }

  loadTable(){
    this.http.get("http://localhost:8080/vehicle/get-all").subscribe(data =>{
      console.log(data);
      this.vehicleList = data;
    })
  }

  deleteVehicle(vehicleId: number) {
    this.http.delete(`http://localhost:8080/vehicle/delete-by-id/${vehicleId}`).subscribe(data => {
      this.deleteSuccessAlert();
      this.loadTable();
    })
  }

  
  updateVehicle(vehicle:any) {
    console.log(vehicle);
    this.vehicleTemp = vehicle;
  }

  saveVehicle(){
    this.http.put("http://localhost:8080/vehicle/update-vehicle",this.vehicleTemp).subscribe(data => {
      this.updateSuccessAlert();
      this.loadTable();
    })
  }

  searchVehicle(searchQuery: string) {
    if (!searchQuery || searchQuery.trim() === '') {
      this.loadTable();
      return;
    }
    const vehicleId = parseInt(searchQuery);

    if (isNaN(vehicleId)) {
      this.errorAlert();
      return;
    }

    this.http.get(`http://localhost:8080/vehicle/search-by-id/${vehicleId}`)
      .subscribe({
        next: (response: any) => {
          console.log('Search response:', response);
          if (response) {
            this.vehicleList = [response];
          } else {
            this.vehicleList = [];
            this.noVehicleErrorAlert();
          }
        }, 
      });
  }




  public deleteSuccessAlert(){
    Swal.fire({
      title: "The Vehicle Deleted Successfully?",
      icon: "success",
      background:"#fff",
    });
  }

  public updateSuccessAlert(){
    Swal.fire({
      title: "The Vehicle Updated Successfully?",
      icon: "success",
      background:"#fff",
    });
  }

  public errorAlert(){
    Swal.fire({
      title: "Please enter a valid numeric ID",
      icon: "error",
      background:"#fff",
    });
  }

  public noVehicleErrorAlert(){
    Swal.fire({
      title: "No vehicle found with this ID",
      icon: "question",
      background:"#fff",
    });
  }

}
