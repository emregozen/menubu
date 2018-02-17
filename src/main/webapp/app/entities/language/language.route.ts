import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { LANGUAGEComponent } from './language.component';
import { LANGUAGEDetailComponent } from './language-detail.component';
import { LANGUAGEPopupComponent } from './language-dialog.component';
import { LANGUAGEDeletePopupComponent } from './language-delete-dialog.component';

export const lANGUAGERoute: Routes = [
    {
        path: 'language',
        component: LANGUAGEComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LANGUAGES'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'language/:id',
        component: LANGUAGEDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LANGUAGES'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const lANGUAGEPopupRoute: Routes = [
    {
        path: 'language-new',
        component: LANGUAGEPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LANGUAGES'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'language/:id/edit',
        component: LANGUAGEPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LANGUAGES'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'language/:id/delete',
        component: LANGUAGEDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LANGUAGES'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
