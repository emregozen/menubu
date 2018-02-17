import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LANGUAGE } from './language.model';
import { LANGUAGEPopupService } from './language-popup.service';
import { LANGUAGEService } from './language.service';

@Component({
    selector: 'jhi-language-delete-dialog',
    templateUrl: './language-delete-dialog.component.html'
})
export class LANGUAGEDeleteDialogComponent {

    lANGUAGE: LANGUAGE;

    constructor(
        private lANGUAGEService: LANGUAGEService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.lANGUAGEService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'lANGUAGEListModification',
                content: 'Deleted an lANGUAGE'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-language-delete-popup',
    template: ''
})
export class LANGUAGEDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private lANGUAGEPopupService: LANGUAGEPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.lANGUAGEPopupService
                .open(LANGUAGEDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
