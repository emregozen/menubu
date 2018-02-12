/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MenubuTestModule } from '../../../test.module';
import { LANGUAGEComponent } from '../../../../../../main/webapp/app/entities/language/language.component';
import { LANGUAGEService } from '../../../../../../main/webapp/app/entities/language/language.service';
import { LANGUAGE } from '../../../../../../main/webapp/app/entities/language/language.model';

describe('Component Tests', () => {

    describe('LANGUAGE Management Component', () => {
        let comp: LANGUAGEComponent;
        let fixture: ComponentFixture<LANGUAGEComponent>;
        let service: LANGUAGEService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MenubuTestModule],
                declarations: [LANGUAGEComponent],
                providers: [
                    LANGUAGEService
                ]
            })
            .overrideTemplate(LANGUAGEComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LANGUAGEComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LANGUAGEService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new LANGUAGE(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.lANGUAGES[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
