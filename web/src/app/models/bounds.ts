import { Coordinate } from './coordinate';

export class Bounds {
  private maxLatitude: number;
  private minLatitude: number;
  private maxLongitude: number;
  private minLongitude: number;

  constructor(maxLatitude, minLatitude, maxLongitude, minLongitude) {
    this.maxLatitude = maxLatitude;
    this.minLatitude = minLatitude;
    this.maxLongitude = maxLongitude;
    this.minLongitude = minLongitude;
  }

  contains(cordinate: Coordinate): boolean {
    return (
      cordinate.latitude <= this.maxLatitude &&
      cordinate.latitude >= this.minLatitude &&
      cordinate.longitude <= this.maxLongitude &&
      cordinate.longitude >= this.minLongitude
    );
  }
}
