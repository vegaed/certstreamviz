import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { CertStore } from './state/cert-store';
import { TableComponent } from './table/table.component';
import { MapComponent } from './map/map.component';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { LeafletMarkerClusterModule } from '@asymmetrik/ngx-leaflet-markercluster';
import { FilterComponent } from './filter/filter.component';

@NgModule({
  declarations: [AppComponent, TableComponent, MapComponent, FilterComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    LeafletModule.forRoot(),
    LeafletMarkerClusterModule.forRoot()
  ],
  providers: [CertStore],
  bootstrap: [AppComponent]
})
export class AppModule {}
