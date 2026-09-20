import { ExpenditureCategory } from "../../types/expenditure-category";
import { PaymentMethod } from "../../types/payment-method";

export interface CreateExpenditureRequest {
    userId: string;
    name: string;
    category: ExpenditureCategory;
    amount: number;
    expenditureDate: Date;
    paymentMethod: PaymentMethod;
    vendor: string;
    receipt: File | null;
    notes: string;
}