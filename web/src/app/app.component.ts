import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { CertStore } from './state/cert-store';
import { Cert } from './models/cert';
import { filter, map, retry, share } from 'rxjs/operators';
import { Observable, BehaviorSubject, combineLatest, Subject } from 'rxjs';
import { take } from 'rxjs/operators';
import { Bounds } from './models/bounds';
import { Coordinate } from './models/coordinate';
import { EventSourceService } from './eventsource.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppComponent implements OnInit {
  title = 'Certstream Viz';

  filteredCerts$ = new BehaviorSubject<Cert[]>([]);
  // inital bounds are the whole world.
  bounds$ = new BehaviorSubject<Bounds>(new Bounds(90, -90, 180, -180));
  // no text filter
  textFilter$ = new BehaviorSubject<string>('');
  // only show geo items
  geoFilter$ = new BehaviorSubject<boolean>(false);
  coordinateSelected$ = new Subject<Coordinate>();

  unfilteredCerts$ = this.certStore
    .select()
    .unbounded()
    .pipe(map(certState => certState.certs));

  // combine these three observables so that when one chages the filtering can happen again and send a new set of certs
  certs$ = combineLatest(
    this.unfilteredCerts$,
    this.bounds$,
    this.geoFilter$
  ).pipe(
    map((tuple: [Cert[], Bounds, boolean]) => {
      const certs = tuple['0'];
      const bounds = tuple['1'];
      const geoFilter = tuple['2'];
      return certs.filter(cert => {
        const coords = cert.coordinate;
        // if no coordinates handled if included or not by the toggle
        if (coords == null) {
          return geoFilter;
        }
        return bounds.contains(coords);
      });
    }),
    share()
  );

  // get certstore from depencency injections
  constructor(
    private certStore: CertStore,
    private eventSourceService: EventSourceService
  ) {}

  ngOnInit(): void {
    // pipe into retry so that if there is an issue the service will resubscribe then
    // subscribe to events and add them to the store.
    this.eventSourceService.getEvents$
      .pipe(retry())
      .subscribe((msg: string) => {
        const cert: Cert = JSON.parse(msg);
        this.certStore.addCert(cert);
      });
  }

  // mappped to table filtered output
  filteredCertsChange(certs: Cert[]) {
    this.filteredCerts$.next(certs);
  }

  // mapped to the table place marker click
  clickedCoordinate(coordinate: Coordinate) {
    this.coordinateSelected$.next(coordinate);
  }
  // mapped to the map bounds changes
  boundsChange(bounds: Bounds) {
    this.bounds$.next(bounds);
  }

  // mapped to the filter toggle change
  filterGeoChange(checked: boolean) {
    this.geoFilter$.next(checked);
  }

  // mapped to the filter text change
  filterTextChange(text: string) {
    this.textFilter$.next(text);
  }
}
