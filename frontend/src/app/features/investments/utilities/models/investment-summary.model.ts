import { InvestmentType } from "../types/investment-type";

export interface InvestmentSummary {
    investmentId: string;
    investmentName: string;
    investmentType: InvestmentType;
    totalValue: number;
}