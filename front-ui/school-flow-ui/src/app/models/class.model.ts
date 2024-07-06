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
    professors?: Array<Professor>,
    cretedDate?: Date,
    lastModifiedDate?: Date
}

export enum Section {
    PRIMARY,
    SECONDARY,
    HIGH_SCHOOL,
    UNIVERSITY
}