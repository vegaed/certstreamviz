import {
  Component,
  OnInit,
  ViewChild,
  ChangeDetectionStrategy,
  Input,
  Output,
  EventEmitter,
  ChangeDetectorRef,
  AfterViewChecked
} from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import { TableDataSource } from './table-datasource';
import { Observable } from 'rxjs';
import { Cert } from '../models/cert';
import { ImprovedTableDataSource } from './improved-table-datasource';

@Component({
  selector: 'app-cert-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class TableComponent implements OnInit, AfterViewChecked {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @Input() certs$: Observable<Cert[]>;
  @Input() textFilter$: Observable<string>;
  @Output() filteredCerts = new EventEmitter<Cert[]>();
  dataSource: TableDataSource;
  improvedDataSource: ImprovedTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['cn', 'issuer', 'source'];

  constructor(private cdRef: ChangeDetectorRef) {}
  ngAfterViewChecked(): void {}

  ngOnInit() {
    this.improvedDataSource = new ImprovedTableDataSource(
      this.certs$,
      this.paginator,
      this.sort
    );

    this.improvedDataSource
      .getDataChangedObservable()
      .subscribe((changed: boolean) => {
        const filteredData = this.improvedDataSource.filteredData;
        this.filteredCerts.emit(filteredData);
        /*  having to trigger change detection manually
        on the mat-table on changes after data stops changing */
        this.cdRef.detectChanges();
      });

    this.textFilter$.subscribe(text => {
      this.improvedDataSource.applyFilter(text);
    });
  }
}
