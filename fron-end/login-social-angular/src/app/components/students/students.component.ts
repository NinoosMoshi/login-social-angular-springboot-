import { Student } from './../../model/student';
import { StudentService } from './../../services/student.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  students: Student[]

  constructor(private stud: StudentService) { }

  ngOnInit(): void {
    this.getStudents();
  }

  getStudents(){
    this.stud.getStudents().subscribe(
      data =>{
        this.students = data
      }
    )
  }

}
