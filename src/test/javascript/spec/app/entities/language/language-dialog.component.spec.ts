/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { MenubuTestModule } from '../../../test.module';
import { LANGUAGEDialogComponent } from '../../../../../../main/webapp/app/entities/language/language-dialog.component';
import { LANGUAGEService } from '../../../../../../main/webapp/app/entities/language/language.service';
import { LANGUAGE } from '../../../../../../main/webapp/app/entities/language/language.model';

describe('Component Tests', () => {

    describe('LANGUAGE Management Dialog Component', () => {
        let comp: LANGUAGEDialogComponent;
        let fixture: ComponentFixture<LANGUAGEDialogComponent>;
        let service: LANGUAGEService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MenubuTestModule],
                declarations: [LANGUAGEDialogComponent],
                providers: [
                    LANGUAGEService
                ]
            })
            .overrideTemplate(LANGUAGEDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LANGUAGEDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LANGUAGEService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new LANGUAGE(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.lANGUAGE = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'lANGUAGEListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new LANGUAGE();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.lANGUAGE = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'lANGUAGEListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
