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
    degree?: string,
    classIds?: string[],
    coursesId?: string[],
    classes?: Array<Class>,
    courses?: Array<Course>,
    Course?: Course
}