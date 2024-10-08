import { Class } from "./class.model";
import { Course } from "./course.model";

export interface Professor {
    id?: string,
    matricule?: string,
    firstname?: string,
    lastname?: string,
    dateOfBirth?: Date,
    genre?: string,
    email?: string,
    phone?: string,
    degree?: string,
    address?: string,
    imageUrl?: string,
    hoursRate?: number,
    classIDs?: string[],
    courseIDs?: string[],
    classes?: Array<Class>,
    courses?: Array<Course>,
}