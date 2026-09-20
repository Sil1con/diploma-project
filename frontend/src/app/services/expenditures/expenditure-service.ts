import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Expenditure } from '../../features/expenditures/utilities/models/expenditure';
import { Observable } from 'rxjs';
import { CategorySummary } from '../../features/expenditures/utilities/models/category-summary';
import { CreateExpenditureRequest } from '../../features/expenditures/utilities/models/request/create-expenditure-request';

@Injectable({
  providedIn: 'root',
})
export class ExpenditureService {
  private readonly apiUrl: string = 'http://localhost:8080/api/expenditures';

  constructor(private httpClient: HttpClient) {}

  createExpenditure(expenditureRequest: CreateExpenditureRequest): Observable<Expenditure> {
    const { receipt, ...expenditureData } = expenditureRequest;

    const formData = new FormData();

    formData.append(
      'expenditure',
      new Blob(
        [JSON.stringify(expenditureData)],
        { type: 'application/json' }
      )
    );

    if (receipt) {
      formData.append('receipt', receipt);
    }

    return this.httpClient.post<Expenditure>(
      `${this.apiUrl}`,
      formData
    );
  }

  getCurrentMonthExpenditures(userId: string): Observable<Expenditure[]> {
    return this.httpClient.get<Expenditure[]>(
      `${this.apiUrl}/current-month/${userId}`
    );
  }

  getExpendituresForPeriod(userId: string, startDate: Date, endDate: Date): Observable<Expenditure[]> {
    const params = new HttpParams()
      .set('startDate', startDate.toDateString())
      .set('endDate', endDate.toDateString());

    return this.httpClient.get<Expenditure[]>(
      `${this.apiUrl}/current-month/${userId}`,
      { params }
    );
  }

  getCurrentMonthCategoriesSummary(userId: string): Observable<CategorySummary[]> {
    return this.httpClient.get<CategorySummary[]>(
      `${this.apiUrl}/current-month-summary/${userId}`,
    );
  }

  getCategoriesSummaryForPeriod(userId: string, startDate: Date, endDate: Date): Observable<CategorySummary[]> {
    const params = new HttpParams()
      .set('startDate', startDate.toDateString())
      .set('endDate', endDate.toDateString());

    return this.httpClient.get<CategorySummary[]>(
      `${this.apiUrl}/${userId}/period/summary`,
      { params }
    );
  }
}
