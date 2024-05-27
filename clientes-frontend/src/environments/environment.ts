// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.


export const environment = {
    production: false,
    tokenServiceUrl: 'http://localhost:8080/public/api/auth/login',
    userServiceUrl: 'http://localhost:8080/api/users/login',
    roleServiceUrl: 'http://localhost:8080/api/roles',
    urlBase: 'http://localhost:8080/api/',
    usernameApi:'Soulreavers1214@gmail.com',
    userPasswordApi:'Taylor/1214.'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
