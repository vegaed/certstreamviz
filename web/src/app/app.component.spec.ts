import {
  async,
  TestBed,
  ComponentFixture,
  fakeAsync,
  tick
} from '@angular/core/testing';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { LeafletMarkerClusterModule } from '@asymmetrik/ngx-leaflet-markercluster';
import * as gs from '@w11k/tydux/dist/global-state';
import { AppComponent } from './app.component';
import { FilterComponent } from './filter/filter.component';
import { MapComponent } from './map/map.component';
import { MaterialModule } from './material.module';
import { CertStore } from './state/cert-store';
import { TableComponent } from './table/table.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModuleCompileResult } from '@angular/compiler/src/ng_module_compiler';
import { Observable, Observer } from 'rxjs';
import { EventSourceService } from './eventsource.service';
import { Cert } from './models/cert';
import { Coordinate } from './models/coordinate';

const coordinate = new Coordinate();
coordinate.longitude = 0;
coordinate.latitude = 0;
const cert = new Cert();
cert.cn = 'cn';
cert.coordinate = coordinate;

class MockEventSourceService extends EventSourceService {
  public get getEvents$(): Observable<string> {
    return Observable.create((observer: Observer<string>) => {
      observer.next(JSON.stringify(cert));

      observer.complete();

      // Note that this is optional, you do not have to return this if you require no cleanup
      return function() {
        console.log('disposed');
      };
    });
  }
}
let component: AppComponent;
let fixture: ComponentFixture<AppComponent>;
describe('AppComponent', () => {
  beforeEach(async(() => {
    gs.resetTydux();
    TestBed.configureTestingModule({
      imports: [
        BrowserAnimationsModule,
        MaterialModule,
        LeafletModule.forRoot(),
        LeafletMarkerClusterModule.forRoot()
      ],
      declarations: [
        AppComponent,
        TableComponent,
        FilterComponent,
        MapComponent
      ],
      providers: [
        CertStore,
        { provide: EventSourceService, useClass: MockEventSourceService }
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
  });

  it('should create the app', async(() => {
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));

  it(`should have as title 'Certstream Viz'`, async(() => {
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Certstream Viz');
  }));

  it(
    `should be subscribe eventsource and producing certs`,
    fakeAsync(() => {
      let actualCerts: Cert[];
      const sub = component.certs$.subscribe((certs: Cert[]) => {
        actualCerts = certs;
      });
      fixture.detectChanges();
      tick(2000);
      sub.unsubscribe();
      expect(actualCerts.length).toEqual(1);
    })
  );

  it('should render app-filter', () => {
    const app = fixture.debugElement.nativeElement;
    const filter: NodeList = app.querySelectorAll('app-filter');
    expect(filter.length).toBe(1);
  });

  it('should render app-map', () => {
    const app = fixture.debugElement.nativeElement;
    const filter: NodeList = app.querySelectorAll('app-map');
    expect(filter.length).toBe(1);
  });

  it('should render app-cert-table', () => {
    const app = fixture.debugElement.nativeElement;
    const filter: NodeList = app.querySelectorAll('app-cert-table');
    expect(filter.length).toBe(1);
  });
});
