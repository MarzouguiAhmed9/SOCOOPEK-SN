/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { findallfeedbackbyproductid } from '../fn/feed-back/findallfeedbackbyproductid';
import { Findallfeedbackbyproductid$Params } from '../fn/feed-back/findallfeedbackbyproductid';
import { PageResponseFeedbackresponse } from '../models/page-response-feedbackresponse';
import { savefeedback } from '../fn/feed-back/savefeedback';
import { Savefeedback$Params } from '../fn/feed-back/savefeedback';

@Injectable({ providedIn: 'root' })
export class FeedBackService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `savefeedback()` */
  static readonly SavefeedbackPath = '/feedbacks';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `savefeedback()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  savefeedback$Response(params: Savefeedback$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return savefeedback(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `savefeedback$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  savefeedback(params: Savefeedback$Params, context?: HttpContext): Observable<number> {
    return this.savefeedback$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `findallfeedbackbyproductid()` */
  static readonly FindallfeedbackbyproductidPath = '/feedbacks/product/{product-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findallfeedbackbyproductid()` instead.
   *
   * This method doesn't expect any request body.
   */
  findallfeedbackbyproductid$Response(params: Findallfeedbackbyproductid$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFeedbackresponse>> {
    return findallfeedbackbyproductid(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findallfeedbackbyproductid$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findallfeedbackbyproductid(params: Findallfeedbackbyproductid$Params, context?: HttpContext): Observable<PageResponseFeedbackresponse> {
    return this.findallfeedbackbyproductid$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseFeedbackresponse>): PageResponseFeedbackresponse => r.body)
    );
  }

}
