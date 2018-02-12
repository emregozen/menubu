import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LANGUAGE } from './language.model';
import { LANGUAGEPopupService } from './language-popup.service';
import { LANGUAGEService } from './language.service';

@Component({
    selector: 'jhi-language-dialog',
    templateUrl: './language-dialog.component.html'
})
export class LANGUAGEDialogComponent implements OnInit {

    lANGUAGE: LANGUAGE;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private lANGUAGEService: LANGUAGEService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.lANGUAGE.id !== undefined) {
            this.subscribeToSaveResponse(
                this.lANGUAGEService.update(this.lANGUAGE));
        } else {
            this.subscribeToSaveResponse(
                this.lANGUAGEService.create(this.lANGUAGE));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<LANGUAGE>>) {
        result.subscribe((res: HttpResponse<LANGUAGE>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: LANGUAGE) {
        this.eventManager.broadcast({ name: 'lANGUAGEListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-language-popup',
    template: ''
})
export class LANGUAGEPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private lANGUAGEPopupService: LANGUAGEPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.lANGUAGEPopupService
                    .open(LANGUAGEDialogComponent as Component, params['id']);
            } else {
                this.lANGUAGEPopupService
                    .open(LANGUAGEDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
