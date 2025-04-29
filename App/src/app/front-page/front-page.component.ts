import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-front-page',
  imports: [RouterLink,FormsModule],
  templateUrl: './front-page.component.html',
  styleUrl: './front-page.component.css'
})
export class FrontPageComponent {
  closeChat() {
    // document.querySelector('.ai-assistant')!.style.display = 'none';
  }

}
