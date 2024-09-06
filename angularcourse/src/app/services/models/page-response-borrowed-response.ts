/* tslint:disable */
/* eslint-disable */
import { BorrowedResponse } from '../models/borrowed-response';
export interface PageResponseBorrowedResponse {
  content?: Array<BorrowedResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalelements?: number;
  totalpages?: number;
}
