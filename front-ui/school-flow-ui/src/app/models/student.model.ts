import {Class} from "./class.model";
import {Address} from "node:cluster";
import {Ecolage} from "./ecolage.model";
import {Note} from "./note.model";

export interface Student {
    id?: string,
    matricule?: string,
    firstname?: string,
    lastname?: string,
    dateOfBirth?: Date,
    birthPlace?: string,
    genre?: string,
    email?: string,
    phone?: string,
    address?: string,
    fathersName?: string,
    mothersName?: string,
    imageUrl?: string,
    classID?: string,
    aClass?: Class,
    ecolages?: Array<Ecolage>,
    note?: Note,
    report?: Report,
    originSchool?: string,
    createdDate?: Date,
    lastUpdateDate?: Date
}
