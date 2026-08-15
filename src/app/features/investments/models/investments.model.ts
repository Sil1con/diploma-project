export type Investment =
  | StockInvestment
  | EtfInvestment
  | CryptoInvestment
  | CommodityInvestment
  | BondInvestment
  | CashInvestment;

export interface InvestmentBase {
  id: number;
  type: string;
  investmentName: string;
  purchaseDate: string;
  purchasePrice: number;
  quantity: number;
  notes: string;
}

export interface StockInvestment extends InvestmentBase {
  ticker: string;
  brokerAccount: string;
}

export interface EtfInvestment extends InvestmentBase {
  ticker: string;
  brokerAccount: string;
}

export interface CryptoInvestment extends InvestmentBase {
  symbol: string;
  wallet: string;
}

export interface CommodityInvestment extends InvestmentBase {
  symbol: string;
}

export interface BondInvestment extends InvestmentBase {
  issuer: string;
  faceValue: number;
  couponRate: number;
  maturityDate: string;
}

export interface CashInvestment extends InvestmentBase {
  amount: number;
}