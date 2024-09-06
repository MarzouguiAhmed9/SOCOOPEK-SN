/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseFeedbackresponse } from '../../models/page-response-feedbackresponse';

export interface Findallfeedbackbyproductid$Params {
  'product-id': number;
  size?: number;
  page?: number;
}

export function findallfeedbackbyproductid(http: HttpClient, rootUrl: string, params: Findallfeedbackbyproductid$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFeedbackresponse>> {
  const rb = new RequestBuilder(rootUrl, findallfeedbackbyproductid.PATH, 'get');
  if (params) {
    rb.path('product-id', params['product-id'], {});
    rb.query('size', params.size, {});
    rb.query('page', params.page, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseFeedbackresponse>;
    })
  );
}

findallfeedbackbyproductid.PATH = '/feedbacks/product/{product-id}';
