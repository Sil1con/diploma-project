import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'percent',
})
export class CustomPercentPipe implements PipeTransform {
  result!: string;

  transform(value: number): string {
    if (!value) {
      return '0%';
    }

    const absolute = Math.abs(value);
    const result = absolute.toFixed(2) + '%';
    
    return result;
  }
}
