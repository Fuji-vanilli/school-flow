import {Course} from "./course.model";

export interface Note {
  date?: Date,
  period?: Period,
  values?: Map<Course, number>
}

export enum Period {
  BIMESTRE_I,
  BIMESTRE_II,
  BIMESTRE_III,
  BIMESTRE_IV,
  BIMESTRE_V,
  TRIMESTRE_I,
  TRIMESTRE_II,
  TRIMESTRE_III,
  SEMESTRE_I,
  SEMESTRE_II
}
