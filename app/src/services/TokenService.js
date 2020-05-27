import AsyncStorage from "@react-native-community/async-storage";
import tokenStore from "../store/TokenStore";

const tokenService = {
  getToken: async (userName, password) => {
    var token = await fetch("http://localhost:8081/uaa/oauth/token", {
      method: "POST",
      headers: {
        Authorization: "Basic YWRtaW5hcHA6cGFzc3dvcmQ=",
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body:
        "grant_type=password&username=" + userName + "+&password=" + password,
    })
      .then((x) => x.json())
      .then(async (resp) => {
        if (!resp.hasOwnProperty("error")) {
          await AsyncStorage.setItem("access_token", resp["access_token"]);
          await AsyncStorage.setItem("email", resp["email"]);
          await AsyncStorage.setItem("refresh_token", resp["refresh_token"]);
          await AsyncStorage.setItem("userId", resp["userId"].toString());
          await AsyncStorage.setItem(
            "expires_in",
            resp["expires_in"].toString()
          );

          tokenStore.setAccessToken(resp["access_token"]);
        }
      })
      .catch((err) => {
        console.log(err);
      });

    return token;
  },
};

export default tokenService;
