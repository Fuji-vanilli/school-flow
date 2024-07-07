export interface Ecolage {
  month?: Month,
  year?: string,
  paymentDate?: Date,
  amount?: number,
  status?: boolean

}

export enum Month {
  JANUARY = "JANUARY",
  FEBRUARY = "FEBRUARY",
  MARCH = "MARCH",
  APRIL = "APRIL",
  MAY = "MAY",
  JUNE = "JUNE",
  JULY = "JULY",
  AUGUST = "AUGUST",
  SEPTEMBER = "SEPTEMBER",
  OCTOBER = "OCTOBER",
  NOVEMBER = "NOVEMBER",
  DECEMBER = "DECEMBER"
}

const MonthNames: { [key in Month]: string } = {
  [Month.JANUARY]: "Janvier",
  [Month.FEBRUARY]: "Février",
  [Month.MARCH]: "Mars",
  [Month.APRIL]: "Avril",
  [Month.MAY]: "Mai",
  [Month.JUNE]: "Juin",
  [Month.JULY]: "Juillet",
  [Month.AUGUST]: "Août",
  [Month.SEPTEMBER]: "Septembre",
  [Month.OCTOBER]: "Octobre",
  [Month.NOVEMBER]: "Novembre",
  [Month.DECEMBER]: "Décembre"
};

// Fonction pour obtenir le nom français du mois
function getMonthName(month: Month): string {
  return MonthNames[month];
}
