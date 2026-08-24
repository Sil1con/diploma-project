import { InvestmentType } from "../types/investment-type";

export type Investment =
  | StockInvestment
  | EtfInvestment
  | CryptoInvestment
  | CommodityInvestment
  | BondInvestment
  | CashInvestment;

export interface InvestmentBase {
  id: number;
  type: InvestmentType;
  investmentName: string;
  purchaseDate: string;
  purchasePrice: number;
  quantity: number;
  notes: string;
}

export interface StockInvestment extends InvestmentBase {
  type: 'STOCK';
  ticker: string;
  brokerAccount: string;
}

export interface EtfInvestment extends InvestmentBase {
  type: 'ETF';
  ticker: string;
  brokerAccount: string;
}

export interface CryptoInvestment extends InvestmentBase {
  type: 'CRYPTO';
  symbol: string;
  wallet: string;
}

export interface CommodityInvestment extends InvestmentBase {
  type: 'COMMODITY';
  symbol: string;
}

export interface BondInvestment extends InvestmentBase {
  type: 'BOND';
  issuer: string;
  faceValue: number;
  couponRate: number;
  maturityDate: string;
}

export interface CashInvestment extends InvestmentBase {
  type: 'CASH';
}