import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private readonly TOKEN_KEY = 'token';

  set token(token: string) {
    if (token) {
      localStorage.setItem(this.TOKEN_KEY, token);
    } else {
      localStorage.removeItem(this.TOKEN_KEY);
    }
  }

  // @ts-ignore
  get token(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  constructor() { }

  /**
   * Check if the token exists in localStorage.
   */
  hasToken(): boolean {
    return !!this.token;
  }

  /**
   * Clear the token from localStorage.
   */
  clearToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }
}
