export class PatientModel {
  bloodGroup?: string;
  height?: string;
  weight?: string;
  user?: PersonModel[];
}

export class PersonModel {
  prefix?: string;
  firstName?: string;
  lastName?: string;
  phoneNumber?: string;
  dob?: Date;
  gender?: string;
  // role? : string;
  account?: AccountModel[];
}

export class AccountModel {
  emailId?: string;
  password?: string;
}

export class Prefix {
  prefix?: string;
  abbreviation?: string;
}

export class Gender {
  gender?: string;
}

export class Role {
  role?: string;
}

export class Enrollment {
  enrollemnt?: any;
}

export class Credentials {
  emailId!: string;
  dob!: Date;
  password!: string;
}
