import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { LANGUAGE } from './language.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<LANGUAGE>;

@Injectable()
export class LANGUAGEService {

    private resourceUrl =  SERVER_API_URL + 'api/languages';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/languages';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(lANGUAGE: LANGUAGE): Observable<EntityResponseType> {
        const copy = this.convert(lANGUAGE);
        return this.http.post<LANGUAGE>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(lANGUAGE: LANGUAGE): Observable<EntityResponseType> {
        const copy = this.convert(lANGUAGE);
        return this.http.put<LANGUAGE>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<LANGUAGE>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<LANGUAGE[]>> {
        const options = createRequestOption(req);
        return this.http.get<LANGUAGE[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<LANGUAGE[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<LANGUAGE[]>> {
        const options = createRequestOption(req);
        return this.http.get<LANGUAGE[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<LANGUAGE[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: LANGUAGE = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<LANGUAGE[]>): HttpResponse<LANGUAGE[]> {
        const jsonResponse: LANGUAGE[] = res.body;
        const body: LANGUAGE[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to LANGUAGE.
     */
    private convertItemFromServer(lANGUAGE: LANGUAGE): LANGUAGE {
        const copy: LANGUAGE = Object.assign({}, lANGUAGE);
        copy.cREATEDATE = this.dateUtils
            .convertDateTimeFromServer(lANGUAGE.cREATEDATE);
        copy.uPDATEDATE = this.dateUtils
            .convertDateTimeFromServer(lANGUAGE.uPDATEDATE);
        return copy;
    }

    /**
     * Convert a LANGUAGE to a JSON which can be sent to the server.
     */
    private convert(lANGUAGE: LANGUAGE): LANGUAGE {
        const copy: LANGUAGE = Object.assign({}, lANGUAGE);

        copy.cREATEDATE = this.dateUtils.toDate(lANGUAGE.cREATEDATE);

        copy.uPDATEDATE = this.dateUtils.toDate(lANGUAGE.uPDATEDATE);
        return copy;
    }
}
