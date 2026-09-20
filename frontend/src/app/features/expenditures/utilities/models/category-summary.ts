import { ExpenditureCategory } from "../types/expenditure-category";

export interface CategorySummary {
    category: ExpenditureCategory;
    totalAmount: number;
}