import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalLetterPipe',
})
export class CapitalLetterPipe implements PipeTransform {
  result!: string;

  transform(value: string): string {
    if (!value) {
      return '';
    }

    return value[0].toUpperCase() + value.slice(1).toLowerCase();
  }
}
