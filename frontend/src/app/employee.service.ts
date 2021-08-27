import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './employee';
import { environment } from 'src/environments/environment';
import { EmployeePage } from './employee.page';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getEmployees(page: number, size: number): Observable<EmployeePage> {
      return this.http.get<EmployeePage>(`${this.apiServiceUrl}/api/v1/employee/all?page=${page}&size=${size}`);
  }

  public addEmployee(employee: Employee): Observable<Employee> {
      return this.http.post<Employee>(`${this.apiServiceUrl}/api/v1/employee/add`, employee);
  }

  public updateEmployee(employee: Employee, employeeId: number): Observable<Employee> {
      return this.http.put<Employee>(`${this.apiServiceUrl}/api/v1/employee/update/${employeeId}`, employee);
  }
  public deleteEmployee(employeeId: number): Observable<void> {
      return this.http.delete<void>(`${this.apiServiceUrl}/api/v1/employee/delete/${employeeId}`);
  }
}
