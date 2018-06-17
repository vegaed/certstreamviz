import { CertMutators } from './cert-mutators';
import { CertState } from './cert-state';
import { Store } from '@w11k/tydux/dist/Store';
import { Cert } from '../models/cert';

export class CertStore extends Store<CertMutators, CertState> {
  constructor() {
    super('cert', new CertMutators(), new CertState());
  }
  clearCerts = this.mutate.clearCerts; // simple delegate to the mutator

  addCert(cert: Cert) {
    this.mutate.addCerts(cert); // access the mutator
  }
}
