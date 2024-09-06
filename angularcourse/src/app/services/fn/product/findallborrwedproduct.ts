/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseBorrowedResponse } from '../../models/page-response-borrowed-response';

export interface Findallborrwedproduct$Params {
  page?: number;
}

export function findallborrwedproduct(http: HttpClient, rootUrl: string, params?: Findallborrwedproduct$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedResponse>> {
  const rb = new RequestBuilder(rootUrl, findallborrwedproduct.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseBorrowedResponse>;
    })
  );
}

findallborrwedproduct.PATH = '/product/borrowed';
