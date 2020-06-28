import AsyncStorage from "@react-native-community/async-storage";
import userStore from "../store/UserStore";

const userService = {
  getCurrentUser: async () => {
    const userId = await AsyncStorage.getItem("userId");
    const token = await AsyncStorage.getItem("accessToken");

    const user = await fetch("http://localhost:8084/user/" + userId, {
      method: "GET",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/x-www-form-urlencoded",
      },
    })
      .then((x) => x.json())
      .then(async (resp) => {
        userStore.setEmail(resp["email"]);
        userStore.setId(resp["id"]);
        userStore.setProfilePicture(resp["profilePicture"]);
        userStore.setUsername(resp["username"]);
      })
      .catch((err) => {
        console.log(err);
      });

    return user;
  },
};

export default userService;
