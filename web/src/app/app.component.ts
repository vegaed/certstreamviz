import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { CertStore } from './state/cert-store';
import { Cert } from './models/cert';
import { filter, map, retry, share } from 'rxjs/operators';
import { EventSourcePolyfill } from 'ng-event-source';
import { Observable, BehaviorSubject, combineLatest, Subject } from 'rxjs';
import { take } from 'rxjs/operators';
import { Bounds } from './models/bounds';
import { Coordinate } from './models/coordinate';

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
  textFilter$ = new BehaviorSubject<string>('');
  geoFilter$ = new BehaviorSubject<boolean>(false);
  coordinateSelected$ = new Subject<Coordinate>();

  tempcerts$ = this.certStore
    .select()
    .unbounded()
    .pipe(map(certState => certState.certs));

  certs$ = combineLatest(this.tempcerts$, this.bounds$, this.geoFilter$).pipe(
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

  constructor(private certStore: CertStore) {}

  ngOnInit(): void {
    // Create obserable from event source
    const events: Observable<string> = new Observable(observer => {
      const eventSource = new EventSourcePolyfill(
        'http://localhost:8080/sse',
        {}
      );

      eventSource.onmessage = data => {
        observer.next(data.data);
      };
      eventSource.onopen = a => {
        console.log('open event source' + JSON.stringify(a));
      };
      eventSource.onerror = e => {
        console.log('Error' + e);
        observer.error(e);
      };

      // cleanup
      return () => {
        console.log('disposed');
        eventSource.close();
      };
    });
    const thing = events.pipe(retry()).pipe(take(10));

    // subscribe to events and add them to the store.
    thing.subscribe((msg: string) => {
      const cert: Cert = JSON.parse(msg);
      this.certStore.addCert(cert);
    });
  }

  // mappped to table filtered output
  filteredCertsChange(certs: Cert[]) {
    this.filteredCerts$.next(certs);
  }

  // mapped to the map bounds changes
  boundsChange(bounds: Bounds) {
    this.bounds$.next(bounds);
  }

  filterGeoChange(checked: boolean) {
    this.geoFilter$.next(checked);
  }

  filterTextChange(text: string) {
    this.textFilter$.next(text);
  }

  clickedCoordinate(coordinate: Coordinate) {
    this.coordinateSelected$.next(coordinate);
  }
}
