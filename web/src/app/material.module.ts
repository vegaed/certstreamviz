import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatInputModule,
  MatSlideToggleModule,
  MatTooltipModule
} from '@angular/material';
import { MatFormFieldModule } from '@angular/material/form-field';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatSlideToggleModule,
    MatTooltipModule
  ],
  exports: [
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatSlideToggleModule,
    MatTooltipModule
  ]
})
export class MaterialModule {}
