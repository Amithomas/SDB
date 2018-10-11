 
import { Component } from '@angular/core';

@Component({
  selector: 'gcs-loader',
  template: `
      <div class="bg-loader">
        <div class="loader" id="loader"></div>
      </div>
  `
})
export class LoaderComponent {

  constructor() { }
}
