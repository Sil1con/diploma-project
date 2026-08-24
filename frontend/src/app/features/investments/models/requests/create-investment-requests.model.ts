import { InvestmentType } from "../../types/investment-type";

export type CreateRequest = 
  | CreateStockRequest
  | CreateEtfRequest
  | CreateCryptoRequest
  | CreateCommodityRequest
  | CreateCommodityRequest
  | CreateBondRequest;

export interface CreateInvestmentRequestBase {
    userId: string;
    type: InvestmentType;
    investmentName: string;
    quantity: number;
    pricePerUnit: number;
    purchaseDate: string;
    notes?: string;
}

export interface CreateStockRequest extends CreateInvestmentRequestBase {
  type: 'STOCK';
  ticker: string;
  brokerAccount: string;
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
}