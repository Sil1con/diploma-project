import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Investment } from '../../features/investments/utilities/models/investments.model';
import { Observable } from 'rxjs';
import { InvestmentSummary } from '../../features/investments/utilities/models/investment-summary.model';
import { CreateInvestmentRequest } from '../../features/investments/utilities/models/requests/create-investment-requests.model';
import { TransactionResponse } from '../../features/investments/utilities/models/responses/transaction-response.model';

@Injectable({
  providedIn: 'root',
})
export class InvestmentService {
  private readonly apiUrl = 'http://localhost:8080/api/investments';

  constructor(private httpClient: HttpClient) {}

  getInvestments(userId: string): Observable<Investment[]> {
    return this.httpClient.get<Investment[]>(
      `${this.apiUrl}/${userId}`
    );
  }

  getPortfolioTotalValue(userId: string) {
    return this.httpClient.get<number>(
      `${this.apiUrl}/overview/${userId}`
    );
  }

  getPreviewSummary(userId: string): Observable<InvestmentSummary[]> {
    return this.httpClient.get<InvestmentSummary[]>(
      `${this.apiUrl}/preview/${userId}`
    )
  }

  createInvestment(investment: CreateInvestmentRequest): Observable<TransactionResponse> {
    return this.httpClient.post<TransactionResponse>(
      this.apiUrl, investment
    );
  }
}
