import { BaseEntity } from './../../shared';

export class LANGUAGE implements BaseEntity {
    constructor(
        public id?: number,
        public cODE?: string,
        public nAME?: string,
        public cREATEDATE?: any,
        public cREATEUSER?: string,
        public uPDATEDATE?: any,
        public uPDATEUSER?: string,
        public uSABLEINEMAIL?: string,
        public tROYACODE?: string,
    ) {
    }
}
