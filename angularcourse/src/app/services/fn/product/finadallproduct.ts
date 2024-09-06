/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseProdcutResponse } from '../../models/page-response-prodcut-response';

export interface Finadallproduct$Params {
  page?: number;
  size?: number;
}

export function finadallproduct(http: HttpClient, rootUrl: string, params?: Finadallproduct$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseProdcutResponse>> {
  const rb = new RequestBuilder(rootUrl, finadallproduct.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
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

finadallproduct.PATH = '/product';
