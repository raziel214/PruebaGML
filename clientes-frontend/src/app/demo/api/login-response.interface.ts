export interface UserLoginResponse {

    userName: string;
    userLastName: string;
    userEmail: string;
    userRolId: number;
    dateCreation: string;
    dateUpdate: string;
    dateDelete: string;
    state: number;
    first: number;
    recoveryPass: number;
    isValid: boolean;

}
