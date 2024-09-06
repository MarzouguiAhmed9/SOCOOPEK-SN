/* tslint:disable */
/* eslint-disable */
import { ProdcutResponse } from '../models/prodcut-response';
export interface PageResponseProdcutResponse {
  content?: Array<ProdcutResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalelements?: number;
  totalpages?: number;
}
