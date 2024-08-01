import { Course } from "./course.model";
import { Professor } from "./professor.model";
import { Student } from "./student.model";


export interface Class {
    id?: string,
    level: string,
    section?: Section,
    numberOfStudents?: number,
    maximumCapacity?: number,
    ecolage?: number,
    students?: Array<Student>,
    courses?: Array<Course>,
    professors?: Array<Professor>,
    cretedDate?: Date,
    lastModifiedDate?: Date
}

export enum Section {
    PRESCOLARY,
    PRIMARY,
    SECONDARY,
    HIGH_SCHOOL,
    UNIVERSITY
}