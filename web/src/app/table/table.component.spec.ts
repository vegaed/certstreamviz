import { fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';

import { TableComponent } from './table.component';
import { MaterialModule } from '../material.module';
import { Cert } from '../models/cert';
import { of, Observable } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Coordinate } from '../models/coordinate';
import { DebugElement } from '@angular/core';

describe('TableComponent', () => {
  let component: TableComponent;
  let fixture: ComponentFixture<TableComponent>;
  const expectedCoordinate = new Coordinate();

  beforeEach(
    fakeAsync(() => {
      TestBed.configureTestingModule({
        imports: [BrowserAnimationsModule, MaterialModule],
        declarations: [TableComponent]
      }).compileComponents();

      fixture = TestBed.createComponent(TableComponent);
      component = fixture.componentInstance;
      expectedCoordinate.longitude = 15;
      expectedCoordinate.latitude = -45;
      const cert = new Cert();
      cert.cn = 'cn';
      cert.coordinate = expectedCoordinate;
      const cert2 = new Cert();
      cert2.cn = 'cn2';
      cert2.coordinate = expectedCoordinate;
      component.certs$ = of([cert, cert2]);
      component.textFilter$ = of('');
    })
  );
  it('should compile', () => {
    expect(component).toBeTruthy();
  });

  it('should display certs', () => {
    fixture.detectChanges();
    const tableDe: DebugElement = fixture.debugElement;
    const tableEl: HTMLElement = tableDe.nativeElement;
    const rows = tableEl.querySelectorAll('mat-row');
    expect(rows.length).toBe(2);
  });

  it('should filter certs based on textfilter', () => {
    component.textFilter$ = of('cn2');
    fixture.detectChanges();
    const tableDe: DebugElement = fixture.debugElement;
    const tableEl: HTMLElement = tableDe.nativeElement;
    const rows = tableEl.querySelectorAll('mat-row');
    expect(rows.length).toBe(1);
  });

  it('should emit coordinate when clicked place icon', () => {
    fixture.detectChanges();
    const tableDe: DebugElement = fixture.debugElement;
    const tableEl: HTMLElement = tableDe.nativeElement;
    let actualCoordinate;
    component.clickedCoordinate.subscribe((coordinate: Coordinate) => {
      actualCoordinate = coordinate;
    });
    const span: HTMLElement = tableEl.querySelector('mat-row span');
    span.click();
    expect(actualCoordinate).toBe(expectedCoordinate);
  });

  it('should filter certs based on textfilter and emit them (1)', () => {
    component.textFilter$ = of('cn2');
    let actualCerts;
    component.filteredCerts.subscribe((certs: Cert[]) => {
      actualCerts = certs;
    });
    fixture.detectChanges();
    expect(actualCerts.length).toBe(1);
  });

  it('should filter certs based on textfilter and emit them (2)', () => {
    component.textFilter$ = of('cn');
    let actualCerts;
    component.filteredCerts.subscribe((certs: Cert[]) => {
      actualCerts = certs;
    });
    fixture.detectChanges();
    expect(actualCerts.length).toBe(2);
  });
});
