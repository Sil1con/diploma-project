import { InvestmentType } from "../../types/investment-type";
import { TransactionType } from "../../types/transaction-type";

export interface TransactionResponse {
  investmentId: number;
  transactionId: number;
  investmentName: string;
  investmentType: InvestmentType;
  transactionType: TransactionType;
  quantity: number;
  pricePerUnit: number;
  transactionDate: Date;
  notes: string;
}