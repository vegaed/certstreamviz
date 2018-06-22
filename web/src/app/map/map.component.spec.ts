import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MapComponent } from './map.component';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { LeafletMarkerClusterModule } from '@asymmetrik/ngx-leaflet-markercluster';
import { of } from 'rxjs';
import { Coordinate } from '../models/coordinate';
import { Cert } from '../models/cert';
import { DebugElement } from '@angular/core';

describe('MapComponent', () => {
  let component: MapComponent;
  let fixture: ComponentFixture<MapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [LeafletModule.forRoot(), LeafletMarkerClusterModule.forRoot()],
      declarations: [MapComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MapComponent);
    component = fixture.componentInstance;
    const coordinate = new Coordinate();
    coordinate.longitude = 0;
    coordinate.latitude = 0;
    component.coordinateSelected$ = of(coordinate);
    const cert = new Cert();
    cert.cn = 'cn';
    cert.coordinate = coordinate;
    component.certs$ = of([cert]);
  });

  it('should create', () => {
    fixture.detectChanges();
    expect(component).toBeTruthy();
  });

  it('should have a single marker cluster ', () => {
    fixture.detectChanges();
    const mapDe: DebugElement = fixture.debugElement;
    const mapEl: HTMLElement = mapDe.nativeElement;
    const rows = mapEl.querySelectorAll('.marker-cluster');
    expect(rows.length).toBe(1);
  });

  it('should have a emit bounds on coordinate selection', () => {
    let actualBounds;
    component.bounds.subscribe(bounds => {
      actualBounds = bounds;
    });
    fixture.detectChanges();
    expect(actualBounds).toBeTruthy();
  });
});
