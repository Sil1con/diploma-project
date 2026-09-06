import { IncomeCategory } from "../types/income-category";

export interface IncomeSource {
  id: number;
  name: string;
  amount: number;
  category: IncomeCategory;
  incomeDate: string;
  description: string;
}