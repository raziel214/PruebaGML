export interface User {
    id?: number;
    userName: string;
    userLastName: string;
    userEmail: string;
    dateCreation?: string;
    dateUpdate?: string;
    state?: number;
}

export interface CreateUserRequest {
    userName: string;
    userLastName: string;
    userEmail: string;
    password: string;
}

export interface UpdateUserRequest {
    userName: string;
    userLastName: string;
    userEmail: string;
}
