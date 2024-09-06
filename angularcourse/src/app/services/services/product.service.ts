/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { approuvereturnedproduct } from '../fn/product/approuvereturnedproduct';
import { Approuvereturnedproduct$Params } from '../fn/product/approuvereturnedproduct';
import { borrowproduct } from '../fn/product/borrowproduct';
import { Borrowproduct$Params } from '../fn/product/borrowproduct';
import { finadallproduct } from '../fn/product/finadallproduct';
import { Finadallproduct$Params } from '../fn/product/finadallproduct';
import { findallborrwedproduct } from '../fn/product/findallborrwedproduct';
import { Findallborrwedproduct$Params } from '../fn/product/findallborrwedproduct';
import { findallproductbyowner } from '../fn/product/findallproductbyowner';
import { Findallproductbyowner$Params } from '../fn/product/findallproductbyowner';
import { findallreterneddproduct } from '../fn/product/findallreterneddproduct';
import { Findallreterneddproduct$Params } from '../fn/product/findallreterneddproduct';
import { findbookbyid } from '../fn/product/findbookbyid';
import { Findbookbyid$Params } from '../fn/product/findbookbyid';
import { PageResponseBorrowedResponse } from '../models/page-response-borrowed-response';
import { PageResponseProdcutResponse } from '../models/page-response-prodcut-response';
import { ProdcutResponse } from '../models/prodcut-response';
import { returnborrowproduct } from '../fn/product/returnborrowproduct';
import { Returnborrowproduct$Params } from '../fn/product/returnborrowproduct';
import { savebook } from '../fn/product/savebook';
import { Savebook$Params } from '../fn/product/savebook';
import { updatearchivedstatus } from '../fn/product/updatearchivedstatus';
import { Updatearchivedstatus$Params } from '../fn/product/updatearchivedstatus';
import { updateshareavlestatus } from '../fn/product/updateshareavlestatus';
import { Updateshareavlestatus$Params } from '../fn/product/updateshareavlestatus';
import { uploadproductimage } from '../fn/product/uploadproductimage';
import { Uploadproductimage$Params } from '../fn/product/uploadproductimage';

@Injectable({ providedIn: 'root' })
export class ProductService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `finadallproduct()` */
  static readonly FinadallproductPath = '/product';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `finadallproduct()` instead.
   *
   * This method doesn't expect any request body.
   */
  finadallproduct$Response(params?: Finadallproduct$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseProdcutResponse>> {
    return finadallproduct(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `finadallproduct$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  finadallproduct(params?: Finadallproduct$Params, context?: HttpContext): Observable<PageResponseProdcutResponse> {
    return this.finadallproduct$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseProdcutResponse>): PageResponseProdcutResponse => r.body)
    );
  }

  /** Path part for operation `savebook()` */
  static readonly SavebookPath = '/product';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `savebook()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  savebook$Response(params: Savebook$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return savebook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `savebook$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  savebook(params: Savebook$Params, context?: HttpContext): Observable<number> {
    return this.savebook$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `uploadproductimage()` */
  static readonly UploadproductimagePath = '/product/cover/{product-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadproductimage()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadproductimage$Response(params: Uploadproductimage$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return uploadproductimage(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadproductimage$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadproductimage(params: Uploadproductimage$Params, context?: HttpContext): Observable<{
}> {
    return this.uploadproductimage$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `borrowproduct()` */
  static readonly BorrowproductPath = '/product/borrowproduct/{product-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `borrowproduct()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowproduct$Response(params: Borrowproduct$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return borrowproduct(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `borrowproduct$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowproduct(params: Borrowproduct$Params, context?: HttpContext): Observable<number> {
    return this.borrowproduct$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updateshareavlestatus()` */
  static readonly UpdateshareavlestatusPath = '/product/shareable/{product-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateshareavlestatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateshareavlestatus$Response(params: Updateshareavlestatus$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return updateshareavlestatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateshareavlestatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateshareavlestatus(params: Updateshareavlestatus$Params, context?: HttpContext): Observable<number> {
    return this.updateshareavlestatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `returnborrowproduct()` */
  static readonly ReturnborrowproductPath = '/product/borrow/return/{product-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `returnborrowproduct()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnborrowproduct$Response(params: Returnborrowproduct$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return returnborrowproduct(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `returnborrowproduct$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnborrowproduct(params: Returnborrowproduct$Params, context?: HttpContext): Observable<number> {
    return this.returnborrowproduct$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `approuvereturnedproduct()` */
  static readonly ApprouvereturnedproductPath = '/product/borrow/approve/{product-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `approuvereturnedproduct()` instead.
   *
   * This method doesn't expect any request body.
   */
  approuvereturnedproduct$Response(params: Approuvereturnedproduct$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return approuvereturnedproduct(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `approuvereturnedproduct$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  approuvereturnedproduct(params: Approuvereturnedproduct$Params, context?: HttpContext): Observable<number> {
    return this.approuvereturnedproduct$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updatearchivedstatus()` */
  static readonly UpdatearchivedstatusPath = '/product/archived/{product-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updatearchivedstatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updatearchivedstatus$Response(params: Updatearchivedstatus$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return updatearchivedstatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updatearchivedstatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updatearchivedstatus(params: Updatearchivedstatus$Params, context?: HttpContext): Observable<number> {
    return this.updatearchivedstatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `findbookbyid()` */
  static readonly FindbookbyidPath = '/product/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findbookbyid()` instead.
   *
   * This method doesn't expect any request body.
   */
  findbookbyid$Response(params: Findbookbyid$Params, context?: HttpContext): Observable<StrictHttpResponse<ProdcutResponse>> {
    return findbookbyid(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findbookbyid$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findbookbyid(params: Findbookbyid$Params, context?: HttpContext): Observable<ProdcutResponse> {
    return this.findbookbyid$Response(params, context).pipe(
      map((r: StrictHttpResponse<ProdcutResponse>): ProdcutResponse => r.body)
    );
  }

  /** Path part for operation `findallreterneddproduct()` */
  static readonly FindallreterneddproductPath = '/product/returned';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findallreterneddproduct()` instead.
   *
   * This method doesn't expect any request body.
   */
  findallreterneddproduct$Response(params?: Findallreterneddproduct$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedResponse>> {
    return findallreterneddproduct(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findallreterneddproduct$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findallreterneddproduct(params?: Findallreterneddproduct$Params, context?: HttpContext): Observable<PageResponseBorrowedResponse> {
    return this.findallreterneddproduct$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBorrowedResponse>): PageResponseBorrowedResponse => r.body)
    );
  }

  /** Path part for operation `findallproductbyowner()` */
  static readonly FindallproductbyownerPath = '/product/owner';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findallproductbyowner()` instead.
   *
   * This method doesn't expect any request body.
   */
  findallproductbyowner$Response(params?: Findallproductbyowner$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseProdcutResponse>> {
    return findallproductbyowner(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findallproductbyowner$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findallproductbyowner(params?: Findallproductbyowner$Params, context?: HttpContext): Observable<PageResponseProdcutResponse> {
    return this.findallproductbyowner$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseProdcutResponse>): PageResponseProdcutResponse => r.body)
    );
  }

  /** Path part for operation `findallborrwedproduct()` */
  static readonly FindallborrwedproductPath = '/product/borrowed';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findallborrwedproduct()` instead.
   *
   * This method doesn't expect any request body.
   */
  findallborrwedproduct$Response(params?: Findallborrwedproduct$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedResponse>> {
    return findallborrwedproduct(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findallborrwedproduct$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findallborrwedproduct(params?: Findallborrwedproduct$Params, context?: HttpContext): Observable<PageResponseBorrowedResponse> {
    return this.findallborrwedproduct$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBorrowedResponse>): PageResponseBorrowedResponse => r.body)
    );
  }

}
