import { Employee } from "./employee";

export interface EmployeePage {
    content: Employee[],
    last: boolean;
    totalElements: number;
    totalPages: number;
    size: number;
    number: number;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}