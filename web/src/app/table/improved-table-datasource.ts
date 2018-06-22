import { DataSource } from '@angular/cdk/collections';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { map, flatMap } from 'rxjs/operators';
import { Observable, merge, combineLatest, BehaviorSubject } from 'rxjs';
import { Cert } from '../models/cert';

/**
 * Data source for the Table view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class ImprovedTableDataSource extends MatTableDataSource<Cert> {
  private filter$ = new BehaviorSubject('');
  private dataChanged$: Observable<boolean>;
  constructor(
    certs$: Observable<Cert[]>,
    private matPaginator: MatPaginator,
    private matSort: MatSort
  ) {
    super([]);
    certs$.subscribe(certs => {
      this.data = certs;
    });
    this.paginator = matPaginator;
    this.sort = matSort;

    // used to signal a data change
    this.dataChanged$ = combineLatest(certs$, this.filter$).pipe(
      map(tuple => {
        return true;
      })
    );
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.filter = filterValue;
    if (this.paginator) {
      this.paginator.firstPage();
    }
    this.filter$.next(filterValue);
  }

  getDataChangedObservable(): Observable<boolean> {
    return this.dataChanged$;
  }
}
