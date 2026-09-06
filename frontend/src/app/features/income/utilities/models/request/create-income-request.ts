import { IncomeCategory } from "../../types/income-category";

export interface CreateIncomeRequest {
    userId: string,
    name: string,
    amount: number,
    category: IncomeCategory,
    incomeDate: Date,
    description: string
}