/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseProdcutResponse } from '../../models/page-response-prodcut-response';

export interface Findallproductbyowner$Params {
  page?: number;
}

export function findallproductbyowner(http: HttpClient, rootUrl: string, params?: Findallproductbyowner$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseProdcutResponse>> {
  const rb = new RequestBuilder(rootUrl, findallproductbyowner.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseProdcutResponse>;
    })
  );
}

findallproductbyowner.PATH = '/product/owner';
