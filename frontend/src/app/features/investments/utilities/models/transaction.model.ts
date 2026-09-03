import { Currency } from "../types/currency-type";
import { InvestmentType } from "../types/investment-type";
import { TransactionType } from "../types/transaction-type";

export type Transaction =
  | StockTransaction
  | EtfTransaction
  | CryptoTransaction
  | CommodityTransaction
  | BondTransaction
  | CashTransaction;

export interface TransactionBase {
  investmentId: number;
  transactionId: number;
  investmentName: string;
  investmentType: InvestmentType;
  transactionType: TransactionType;
}

export interface StockTransaction extends TransactionBase {
  investmentType: 'STOCK';
  ticker: string;
}

export interface EtfTransaction extends TransactionBase {
  investmentType: 'ETF';
  ticker: string;
  brokerAccount: string;
}

export interface CryptoTransaction extends TransactionBase {
  investmentType: 'CRYPTO';
  symbol: string;
  wallet: string;
}

export interface CommodityTransaction extends TransactionBase {
  investmentType: 'COMMODITY';
  symbol: string;
}

export interface BondTransaction extends TransactionBase {
  investmentType: 'BOND';
}

export interface CashTransaction extends TransactionBase {
  investmentType: 'CASH';
  currency: Currency;
}