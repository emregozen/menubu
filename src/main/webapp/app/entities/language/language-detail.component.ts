import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { LANGUAGE } from './language.model';
import { LANGUAGEService } from './language.service';

@Component({
    selector: 'jhi-language-detail',
    templateUrl: './language-detail.component.html'
})
export class LANGUAGEDetailComponent implements OnInit, OnDestroy {

    lANGUAGE: LANGUAGE;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private lANGUAGEService: LANGUAGEService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLANGUAGES();
    }

    load(id) {
        this.lANGUAGEService.find(id)
            .subscribe((lANGUAGEResponse: HttpResponse<LANGUAGE>) => {
                this.lANGUAGE = lANGUAGEResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLANGUAGES() {
        this.eventSubscriber = this.eventManager.subscribe(
            'lANGUAGEListModification',
            (response) => this.load(this.lANGUAGE.id)
        );
    }
}
