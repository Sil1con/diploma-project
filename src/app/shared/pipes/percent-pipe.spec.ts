import { CustomPercentPipe } from './percent-pipe';

describe('PercentPipe', () => {
  it('create an instance', () => {
    const pipe = new CustomPercentPipe();
    expect(pipe).toBeTruthy();
  });
});
