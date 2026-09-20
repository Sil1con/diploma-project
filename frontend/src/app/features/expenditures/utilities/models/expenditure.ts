import { ExpenditureCategory } from "../types/expenditure-category";

export interface Expenditure {
  id: string;
  name: string;
  category: ExpenditureCategory;
  amount: number;
  expenditureDate: string;
  paymentMethod: string;
  merchant: string;
  notes: string;
  receipt: File | null;
}