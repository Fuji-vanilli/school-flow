import { Class } from "./class.model";
import { Course } from "./course.model";

export interface Professor {
    id?: string,
    matricule?: string,
    firstname?: string,
    lastname?: string,
    dateOfBirth?: Date,
    email?: string,
    phone?: string,
    classIds?: string[],
    classes?: Array<Class>,
    courseId?: string,
    Course?: Course
}