import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IncomeSource } from '../../features/income/utilities/models/income-source';
import { CreateIncomeRequest } from '../../features/income/utilities/models/request/create-income-request';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class IncomeService {
  private readonly apiUrl = 'http://localhost:8080/api/income';

  constructor(private httpClient: HttpClient) {}

  createIncomeSource(income: CreateIncomeRequest) {
    return this.httpClient.post<IncomeSource>(
      this.apiUrl, income
    )
  }

  getCurrentMonthIncomeSources(userId: string): Observable<IncomeSource[]> {
    return this.httpClient.get<IncomeSource[]>(
      `${this.apiUrl}/${userId}/current-month`
    );
  }

  getCurrentMonthTotalIncome(userId: string): Observable<number> {
    return this.httpClient.get<number>(
      `${this.apiUrl}/${userId}/current-total`
    );
  }

  deletIncomeSource(userId: string, incomeId: string): Observable<void> {
    return this.httpClient.delete<void>(
      `${this.apiUrl}/${userId}/delete/${incomeId}`
    );
  }
}
