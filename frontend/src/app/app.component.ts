import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Employee } from './employee';
import { EmployeePage } from './employee.page';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public employees: Employee[] = [];
  public totalElements: number = 0

  constructor(private employeeService: EmployeeService){}

  ngOnInit() {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees(0, 20).subscribe(
      (response: EmployeePage) => {
        this.employees = response['content'];
        this.totalElements = response['totalElements'];
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onOpenModal(mode: string, employee?: Employee): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');

    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    
    if(mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal')
    }

    if(mode === 'edit') {
      button.setAttribute('data-target', '#editEmployeeModal')
    }

    if(mode === 'delete') {
      button.setAttribute('data-target', '#deleteEmployeeModal')
    }

    container?.appendChild(button);

    button.click();
  }
}
