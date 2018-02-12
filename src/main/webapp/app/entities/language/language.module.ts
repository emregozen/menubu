import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MenubuSharedModule } from '../../shared';
import {
    LANGUAGEService,
    LANGUAGEPopupService,
    LANGUAGEComponent,
    LANGUAGEDetailComponent,
    LANGUAGEDialogComponent,
    LANGUAGEPopupComponent,
    LANGUAGEDeletePopupComponent,
    LANGUAGEDeleteDialogComponent,
    lANGUAGERoute,
    lANGUAGEPopupRoute,
} from './';

const ENTITY_STATES = [
    ...lANGUAGERoute,
    ...lANGUAGEPopupRoute,
];

@NgModule({
    imports: [
        MenubuSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LANGUAGEComponent,
        LANGUAGEDetailComponent,
        LANGUAGEDialogComponent,
        LANGUAGEDeleteDialogComponent,
        LANGUAGEPopupComponent,
        LANGUAGEDeletePopupComponent,
    ],
    entryComponents: [
        LANGUAGEComponent,
        LANGUAGEDialogComponent,
        LANGUAGEPopupComponent,
        LANGUAGEDeleteDialogComponent,
        LANGUAGEDeletePopupComponent,
    ],
    providers: [
        LANGUAGEService,
        LANGUAGEPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MenubuLANGUAGEModule {}
