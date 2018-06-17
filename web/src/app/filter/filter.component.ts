import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MatSlideToggleChange } from '@angular/material';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {
  @Output() filterText = new EventEmitter<string>();
  @Output() filterGeo = new EventEmitter<boolean>();
  geoChecked = false;
  constructor() {}

  ngOnInit() {}

  applyTextFilter(filterValue: string) {
    this.filterText.emit(filterValue);
  }

  applyGeoFilter(change: MatSlideToggleChange) {
    const value = change.checked;
    this.geoChecked = value;
    this.filterGeo.emit(value);
  }
}
