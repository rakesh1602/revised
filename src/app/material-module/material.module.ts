// Copyright 2020-2021 NXGN Management, LLC. All Rights Reserved.

import { ClipboardModule } from '@angular/cdk/clipboard';
import { OverlayModule } from '@angular/cdk/overlay';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatBadgeModule } from '@angular/material/badge';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatTreeModule } from '@angular/material/tree';
// import { NgSelectModule } from '@ng-select/ng-select';
// import { ColorPickerModule } from 'ngx-color-picker';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AppRoutingModule } from '../app-routing.module';


const materialComponents = [
  MatCardModule,
  MatInputModule,
  MatFormFieldModule,
  MatIconModule,
  MatButtonModule,
  MatListModule,
  MatMenuModule,
  MatProgressBarModule,
  FormsModule,
  MatSidenavModule,
  MatToolbarModule,
  MatBadgeModule,
  MatAutocompleteModule,
  MatTreeModule,
  ClipboardModule,
  MatDialogModule,
  MatTooltipModule,
  MatSnackBarModule,
  MatSelectModule,
  MatButtonToggleModule,
  MatTableModule,
  MatSlideToggleModule,
  MatPaginatorModule,
  MatRadioModule,
  MatCheckboxModule,
  MatTabsModule,
  MatDatepickerModule,
  MatNativeDateModule,
  OverlayModule,
  MatDividerModule,
  MatSortModule,
  MatExpansionModule,
  // NgSelectModule,
  // ColorPickerModule,
  MatProgressSpinnerModule,
  AppRoutingModule 
];
@NgModule({
  imports: [materialComponents],
  exports: [materialComponents]
})
export class MaterialModule {}
