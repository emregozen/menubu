/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { MenubuTestModule } from '../../../test.module';
import { LANGUAGEDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/language/language-delete-dialog.component';
import { LANGUAGEService } from '../../../../../../main/webapp/app/entities/language/language.service';

describe('Component Tests', () => {

    describe('LANGUAGE Management Delete Component', () => {
        let comp: LANGUAGEDeleteDialogComponent;
        let fixture: ComponentFixture<LANGUAGEDeleteDialogComponent>;
        let service: LANGUAGEService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MenubuTestModule],
                declarations: [LANGUAGEDeleteDialogComponent],
                providers: [
                    LANGUAGEService
                ]
            })
            .overrideTemplate(LANGUAGEDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LANGUAGEDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LANGUAGEService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
