import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { LANGUAGE } from './language.model';
import { LANGUAGEService } from './language.service';

@Injectable()
export class LANGUAGEPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private lANGUAGEService: LANGUAGEService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.lANGUAGEService.find(id)
                    .subscribe((lANGUAGEResponse: HttpResponse<LANGUAGE>) => {
                        const lANGUAGE: LANGUAGE = lANGUAGEResponse.body;
                        lANGUAGE.cREATEDATE = this.datePipe
                            .transform(lANGUAGE.cREATEDATE, 'yyyy-MM-ddTHH:mm:ss');
                        lANGUAGE.uPDATEDATE = this.datePipe
                            .transform(lANGUAGE.uPDATEDATE, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.lANGUAGEModalRef(component, lANGUAGE);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.lANGUAGEModalRef(component, new LANGUAGE());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    lANGUAGEModalRef(component: Component, lANGUAGE: LANGUAGE): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.lANGUAGE = lANGUAGE;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
