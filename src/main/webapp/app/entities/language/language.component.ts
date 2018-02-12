import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { LANGUAGE } from './language.model';
import { LANGUAGEService } from './language.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-language',
    templateUrl: './language.component.html'
})
export class LANGUAGEComponent implements OnInit, OnDestroy {
lANGUAGES: LANGUAGE[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private lANGUAGEService: LANGUAGEService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search'] ?
            this.activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.lANGUAGEService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: HttpResponse<LANGUAGE[]>) => this.lANGUAGES = res.body,
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
       }
        this.lANGUAGEService.query().subscribe(
            (res: HttpResponse<LANGUAGE[]>) => {
                this.lANGUAGES = res.body;
                this.currentSearch = '';
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInLANGUAGES();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: LANGUAGE) {
        return item.id;
    }
    registerChangeInLANGUAGES() {
        this.eventSubscriber = this.eventManager.subscribe('lANGUAGEListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
