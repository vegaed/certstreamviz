import { CertStore } from './cert-store';
import { Cert } from '../models/cert';
import { TestBed } from '@angular/core/testing';
import * as gs from '@w11k/tydux/dist/global-state';
import { last } from 'rxjs/operators';

describe('Cert Store', () => {
  const certcreate = function() {
    const cert = new Cert();
    cert.cn = Math.random().toString();
    return cert;
  };
  let store: CertStore;
  beforeEach(() => {
    gs.resetTydux();
    store = new CertStore();
  });

  it('addCert should add certs to the store', () => {
    let lastCert;
    for (let i = 0; i < 5; i++) {
      const cert = new Cert();
      cert.cn = Math.random().toString();
      store.addCert(cert);
      lastCert = cert;
    }
    const length = store.state.certs.length;
    expect(length).toBe(5);
    expect(store.state.certs[length - 1]).toBe(lastCert);
  });

  it('clearCerts erase all certs from the store', () => {
    store.addCert(certcreate());
    store.addCert(certcreate());
    expect(store.state.certs.length).toBe(2);
    store.clearCerts();
    expect(store.state.certs.length).toBe(0);
  });
});
