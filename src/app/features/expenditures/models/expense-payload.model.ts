export interface ExpensePayload {
  expenseName: string;
  category: string;
  amount: number;
  expenseDate: string;
  paymentMethod: string;
  merchant: string;
  notes: string;
  receipt: File | null;
}