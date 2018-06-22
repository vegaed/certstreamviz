import { Mutator } from '@w11k/tydux/dist/mutator';
import { CertState } from './cert-state';
import { Cert } from '../models/cert';

export class CertMutators extends Mutator<CertState> {
  clearCerts() {
    this.state.certs = [];
  }

  addCerts(cert: Cert) {
    this.state.certs = [...this.state.certs, cert];
  }
}
