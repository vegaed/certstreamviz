import { Injectable, Inject } from '@angular/core';
import { EventSourcePolyfill } from 'ng-event-source';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EventSourceService {
  private _events$: Observable<string>;

  constructor() {
    // Create obserable from event source
    this._events$ = new Observable(observer => {
      const eventSource = new EventSourcePolyfill(
        environment.event_source_url,
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
  }

  public get getEvents$(): Observable<string> {
    return this._events$;
  }
}
