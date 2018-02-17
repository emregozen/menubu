import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { MenubuRegionMySuffixModule } from './region-my-suffix/region-my-suffix.module';
import { MenubuCountryMySuffixModule } from './country-my-suffix/country-my-suffix.module';
import { MenubuLocationMySuffixModule } from './location-my-suffix/location-my-suffix.module';
import { MenubuDepartmentMySuffixModule } from './department-my-suffix/department-my-suffix.module';
import { MenubuTaskMySuffixModule } from './task-my-suffix/task-my-suffix.module';
import { MenubuEmployeeMySuffixModule } from './employee-my-suffix/employee-my-suffix.module';
import { MenubuJobMySuffixModule } from './job-my-suffix/job-my-suffix.module';
import { MenubuJobHistoryMySuffixModule } from './job-history-my-suffix/job-history-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        MenubuRegionMySuffixModule,
        MenubuCountryMySuffixModule,
        MenubuLocationMySuffixModule,
        MenubuDepartmentMySuffixModule,
        MenubuTaskMySuffixModule,
        MenubuEmployeeMySuffixModule,
        MenubuJobMySuffixModule,
        MenubuJobHistoryMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MenubuEntityModule {}
