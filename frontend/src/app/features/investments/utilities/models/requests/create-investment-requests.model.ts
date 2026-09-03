import { Currency } from "../../types/currency-type";
import { InvestmentType } from "../../types/investment-type";
import { TransactionType } from "../../types/transaction-type";

export type CreateInvestmentRequest = 
  | CreateStockRequest
  | CreateEtfRequest
  | CreateCryptoRequest
  | CreateCommodityRequest
  | CreateBondRequest
  | CreateCashRequest;

export interface CreateInvestmentRequestBase {
    userId: string;
    name: string;
    type: InvestmentType;
    transactionType: TransactionType;
    quantity: number;
    pricePerUnit: number;
    purchaseDate: string;
    notes?: string;
}

export interface CreateStockRequest extends CreateInvestmentRequestBase {
  type: 'STOCK';
  ticker: string;
}

export interface CreateEtfRequest extends CreateInvestmentRequestBase {
  type: 'ETF';
  ticker: string;
  brokerAccount: string;
}

export interface CreateCryptoRequest extends CreateInvestmentRequestBase {
  type: 'CRYPTO';
  symbol: string;
  wallet: string;
}

export interface CreateCommodityRequest extends CreateInvestmentRequestBase {
  type: 'CRYPTO';
  symbol: string;
}

export interface CreateBondRequest extends CreateInvestmentRequestBase {
  type: 'BOND';
  issuer: string;
  faceValue: number;
  couponRate: number;
  maturityDate: string;
}

export interface CreateCashRequest extends CreateInvestmentRequestBase {
  type: 'CASH';
  currency: Currency
}