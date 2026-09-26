import { IncomeCategory } from "../types/income-category";

export interface IncomeSource {
  id: string;
  name: string;
  amount: number;
  category: IncomeCategory;
  incomeDate: string;
  description: string;
}