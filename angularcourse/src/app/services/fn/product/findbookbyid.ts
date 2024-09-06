/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ProdcutResponse } from '../../models/prodcut-response';

export interface Findbookbyid$Params {
  'book-id': number;
}

export function findbookbyid(http: HttpClient, rootUrl: string, params: Findbookbyid$Params, context?: HttpContext): Observable<StrictHttpResponse<ProdcutResponse>> {
  const rb = new RequestBuilder(rootUrl, findbookbyid.PATH, 'get');
  if (params) {
    rb.path('book-id', params['book-id'], {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ProdcutResponse>;
    })
  );
}

findbookbyid.PATH = '/product/{book-id}';
