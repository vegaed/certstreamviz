import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterComponent } from './filter.component';
import { MaterialModule } from '../material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DebugElement } from '@angular/core';

describe('FilterComponent', () => {
  let component: FilterComponent;
  let fixture: ComponentFixture<FilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [BrowserAnimationsModule, MaterialModule],
      declarations: [FilterComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterComponent);
    component = fixture.componentInstance;
    // trigger initial data binding
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render text filter', async(() => {
    const app = fixture.debugElement.nativeElement;
    const filter: NodeList = app.querySelectorAll('.textFilter');
    expect(filter.length).toBe(1);
  }));

  it('toggle should raise event when clicked', () => {
    let checked;
    component.filterGeo.subscribe((check: boolean) => {
      checked = check;
    });
    const filterDe: DebugElement = fixture.debugElement;
    const filterEl: HTMLElement = filterDe.nativeElement;
    const toggleEl: HTMLElement = filterEl.querySelector(
      '.mat-slide-toggle-input'
    );
    toggleEl.click();
    expect(checked).toBe(true);
  });

  it('text should raise event when keyup', () => {
    let text;
    component.filterText.subscribe((changedText: string) => {
      text = changedText;
    });
    const filterDe: DebugElement = fixture.debugElement;
    const filterEl: HTMLElement = filterDe.nativeElement;
    const textEl: HTMLInputElement = filterEl.querySelector(
      '.textFilter input'
    );
    const keyEvent = new KeyboardEvent('keyup');
    textEl.value = 'abc';
    textEl.dispatchEvent(keyEvent);
    expect(text).toBe('abc');
  });
});
