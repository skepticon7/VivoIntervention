export const msalConfig = {
    auth: {
        clientId: "7a54203a-24c5-4d3a-b43a-83146f260ab0",
        authority: "https://login.microsoftonline.com/common",
        redirectUri: "http://localhost:80/home",
    },
    cache: {
        cacheLocation: "sessionStorage",
        storeAuthStateInCookie: false,
    },
};

export const loginRequest = {
    scopes: [
        "Files.ReadWrite.All",
        "User.Read"
    ],
};
