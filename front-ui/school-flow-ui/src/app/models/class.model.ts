import { Professor } from "./professor.model";
import { Student } from "./student.model";


export interface Class {
    id?: string,
    level: Level,
    section?: Section,
    numberOfStudents?: number,
    maximumCapacity?: number,
    ecolage?: number,
    students?: Array<Student>,
    professors?: Array<Professor>,
    cretedDate?: Date,
    lastModifiedDate?: Date
}

export enum Level {
    PS,
    MS,
    GS,
    CP,
    CE1,
    CE2,
    CM1,
    CM2,
    SIXIEME,
    CINQUIEME,
    QUATRIEME,
    TROISIEME,
    SECOND,
    PREMIERE,
    TERMINALE
}

export enum Section {
    PRIMARY,
    SECONDARY,
    HIGH_SCHOOL,
    UNIVERSITY
}