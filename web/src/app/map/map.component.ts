import {
  Component,
  OnInit,
  Input,
  ChangeDetectionStrategy,
  EventEmitter,
  Output,
  AfterViewChecked,
  ChangeDetectorRef
} from '@angular/core';
// import { latLng, tileLayer } from 'leaflet';
import * as L from 'leaflet';
import 'leaflet/dist/images/marker-shadow.png';
import 'leaflet/dist/images/marker-icon.png';
import 'leaflet/dist/images/marker-icon-2x.png';

import 'leaflet.markercluster';
import { Observable } from 'rxjs';
import { Cert } from '../models/cert';
import { map, filter, flatMap } from 'rxjs/operators';
import { Bounds } from '../models/bounds';
import { Coordinate } from '../models/coordinate';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, AfterViewChecked {
  @Input() certs$: Observable<Cert[]>;
  @Input() coordinateSelected$: Observable<Coordinate>;
  @Output() bounds = new EventEmitter<Bounds>();

  options = {
    layers: [
      L.tileLayer(
        'https://cartodb-basemaps-{s}.global.ssl.fastly.net/light_all/{z}/{x}/{y}{r}.png',
        {
          attribution:
            '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a> &copy;' +
            '<a href="http://cartodb.com/attributions">CartoDB</a>',
          subdomains: 'abcd',
          maxZoom: 19
        }
      )
    ],
    zoom: 2,
    center: L.latLng(0, 0)
  };

  markerClusterData: L.Layer[] = [];
  markerClusterOptions: L.MarkerClusterGroupOptions = {
    showCoverageOnHover: false,
    singleMarkerMode: true,
    maxClusterRadius: 60, // default 80
    spiderfyOnMaxZoom: false,
    chunkedLoading: true // needed for large datasets as not to lock the browser
  };

  constructor(private cdRef: ChangeDetectorRef) {}
  ngAfterViewChecked(): void {}
  ngOnInit() {
    const markers$ = this.certs$.pipe(
      map(certs => {
        const data: L.Layer[] = [];
        certs.filter(cert => cert.coordinate !== null).forEach(cert => {
          data.push(
            L.marker([cert.coordinate.latitude, cert.coordinate.longitude])
          );
        });
        return data;
      })
    );

    markers$.subscribe(markerClusterData => {
      this.markerClusterData = markerClusterData;
      this.cdRef.detectChanges();
    });
  }

  onMapReady(leafletMap: L.Map) {
    leafletMap.on('moveend', (event: L.LeafletEvent) => {
      const ne = leafletMap.getBounds().getNorthEast();
      const sw = leafletMap.getBounds().getSouthWest();
      const bounds = new Bounds(ne.lat, sw.lat, ne.lng, sw.lng);
      this.bounds.emit(bounds);
    });
    this.coordinateSelected$.subscribe(coordinate => {
      leafletMap.setView(
        L.latLng(coordinate.latitude, coordinate.longitude),
        10
      );
      console.log('gotit');
    });
  }
}
