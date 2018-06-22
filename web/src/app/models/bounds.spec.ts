import { Cert } from './cert';
import { Bounds } from './bounds';
import { Coordinate } from './coordinate';

describe('Bounds', () => {
  const bounds = new Bounds(45, -45, 45, -45);

  it('should create an instance', () => {
    expect(bounds).toBeTruthy();
  });

  it('contains should be true ', () => {
    const coordinate = new Coordinate();
    coordinate.latitude = 0;
    coordinate.longitude = 0;
    expect(bounds.contains(coordinate)).toBe(true);
  });

  it('contains should be false postive coordinate', () => {
    const coordinate = new Coordinate();
    coordinate.latitude = 90;
    coordinate.longitude = 90;
    expect(bounds.contains(coordinate)).toBe(false);
  });

  it('contains should be false negative coordinate', () => {
    const coordinate = new Coordinate();
    coordinate.latitude = -90;
    coordinate.longitude = -90;
    expect(bounds.contains(coordinate)).toBe(false);
  });

  it('contains should be false mix coordinate', () => {
    const coordinate = new Coordinate();
    coordinate.latitude = 90;
    coordinate.longitude = -90;
    expect(bounds.contains(coordinate)).toBe(false);
  });
});
