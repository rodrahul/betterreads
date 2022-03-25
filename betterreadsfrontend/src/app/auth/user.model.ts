export class User {
  constructor(private _tokenExpirationDate: Date, private _token: string) {}

  public get token(): string {
    const expdate = new Date(this._tokenExpirationDate);
    if (!this._tokenExpirationDate || new Date() > expdate) {
      return null;
    }
    return this._token;
  }
}
