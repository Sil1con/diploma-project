import { InvestmentType } from "../types/investment-type";

export interface InvestmentSummary {
    investmentId: number;
    investmentName: string;
    investmentType: InvestmentType;
    totalValue: number;
}