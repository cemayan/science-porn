import AsyncStorage from "@react-native-community/async-storage";
import tokenService from "./TokenService";
import Error from "../model/Error";

const loginService = {
  login: async (loginObj) => {
    if ((await AsyncStorage.getItem("access_token")) == null) {
      let tokenObj = await tokenService.getToken(
        loginObj.username,
        loginObj.password
      );

      if (tokenObj.hasOwnProperty("error")) {
        return new Error(tokenObj.error, 403);
      }
    }
  },
};

export default loginService;
