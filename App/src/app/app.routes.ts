import { Routes } from '@angular/router';
import { AddVehiclePageComponent } from './add-vehicle-page/add-vehicle-page.component';
import { FrontPageComponent } from './front-page/front-page.component';
import { ViewVehiclePageComponent } from './view-vehicle-page/view-vehicle-page.component';

export const routes: Routes = [
    {
        path: '',
        component: FrontPageComponent,
    },
    {
        path: 'add-vehicle',
        component: AddVehiclePageComponent,
    },
    {
        path: 'view-vehicle',
        component: ViewVehiclePageComponent,
    },

];
