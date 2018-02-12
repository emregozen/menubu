/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { MenubuTestModule } from '../../../test.module';
import { LANGUAGEDetailComponent } from '../../../../../../main/webapp/app/entities/language/language-detail.component';
import { LANGUAGEService } from '../../../../../../main/webapp/app/entities/language/language.service';
import { LANGUAGE } from '../../../../../../main/webapp/app/entities/language/language.model';

describe('Component Tests', () => {

    describe('LANGUAGE Management Detail Component', () => {
        let comp: LANGUAGEDetailComponent;
        let fixture: ComponentFixture<LANGUAGEDetailComponent>;
        let service: LANGUAGEService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MenubuTestModule],
                declarations: [LANGUAGEDetailComponent],
                providers: [
                    LANGUAGEService
                ]
            })
            .overrideTemplate(LANGUAGEDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LANGUAGEDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LANGUAGEService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new LANGUAGE(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.lANGUAGE).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
