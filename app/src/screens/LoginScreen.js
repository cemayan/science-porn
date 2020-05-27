import React from "react";
import {
  Alert,
  Dimensions,
  KeyboardAvoidingView,
  StyleSheet,
  Platform,
  Keyboard,
  TouchableWithoutFeedback,
} from "react-native";

// galio component
import { Block, Button, Input, NavBar, Text } from "galio-framework";
import theme from "../theme";
import AsyncStorage from "@react-native-community/async-storage";
import tokenStore from "../store/TokenStore";
import { inject, observer } from "mobx-react";
import tokenService from "../services/TokenService";

const { height, width } = Dimensions.get("window");

@inject("tokenStore")
@observer
class LoginScreen extends React.Component {
  constructor() {
    super();
    this.state = {
      username: "",
      password: "",
      accessToken: "",
    };
  }
  handleChange = (name, value) => {
    this.setState({ [name]: value });
  };

  // getToken = async (userName, password) => {
  //   var self = this;
  //   var token = await fetch("http://localhost:8081/uaa/oauth/token", {
  //     method: "POST",
  //     headers: {
  //       Authorization: "Basic YWRtaW5hcHA6cGFzc3dvcmQ=",
  //       "Content-Type": "application/x-www-form-urlencoded",
  //     },
  //     body:
  //       "grant_type=password&username=" + userName + "+&password=" + password,
  //   })
  //     .then((x) => x.json())
  //     .then(async (resp) => {
  //       if (!resp.hasOwnProperty("error")) {
  //         await AsyncStorage.setItem("access_token", resp["access_token"]);
  //         await AsyncStorage.setItem("email", resp["email"]);
  //         await AsyncStorage.setItem("refresh_token", resp["refresh_token"]);
  //         await AsyncStorage.setItem("userId", resp["userId"].toString());
  //         await AsyncStorage.setItem(
  //           "expires_in",
  //           resp["expires_in"].toString()
  //         );
  //
  //         this.props.tokenStore.setAccessToken(resp["access_token"]);
  //       }
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     });
  //   return token;
  // };

  render() {
    const { navigation } = this.props;
    const { username, password } = this.state;

    return (
      <TouchableWithoutFeedback onPress={Keyboard.dismiss} accessible={false}>
        <Block safe flex style={{ backgroundColor: theme.COLORS.WHITE }}>
          <KeyboardAvoidingView
            style={styles.container}
            behavior={Platform.OS == "ios" ? "padding" : "position"}
            enabled
          >
            <Block
              flex
              center
              style={{
                marginTop: theme.SIZES.BASE * 1.875,
                marginBottom: height * 0.1,
              }}
            />
            <Block flex={1}>
              <Block flex={1}>
                <Input
                  placeholder="Username"
                  autoCapitalize="none"
                  style={{ width: width * 0.9 }}
                  value={this.state.username}
                  onChangeText={(text) => this.handleChange("username", text)}
                />
                <Input
                  password
                  viewPass
                  placeholder="Password"
                  style={{ width: width * 0.9 }}
                  value={this.state.password}
                  onChangeText={(text) => this.handleChange("password", text)}
                />
                <Text
                  size={theme.SIZES.FONT * 0.75}
                  onPress={() => Alert.alert("Not implemented")}
                  style={{
                    alignSelf: "flex-end",
                    lineHeight: theme.SIZES.FONT * 2,
                  }}
                >
                  Forgot your password?
                </Text>
              </Block>
              <Block flex>
                <Button
                  color="#50C7C7"
                  shadowless
                  onPress={async () => {
                    this.setState({ username: "", password: "" });

                    await tokenService.getToken(
                      this.state.username,
                      this.state.password
                    );

                    if (this.props.tokenStore.getAccessToken == "") {
                      Alert.alert("We have a problem. Please try again soon!");
                      Keyboard.dismiss();
                    }
                  }}
                >
                  Sign in
                </Button>
                <Button
                  color="transparent"
                  shadowless
                  onPress={() => {
                    //navigation.navigate("Register")
                  }}
                >
                  <Text center size={theme.SIZES.FONT * 0.75}>
                    {"Don't have an account? Sign Up"}
                  </Text>
                </Button>
              </Block>
            </Block>
          </KeyboardAvoidingView>
        </Block>
      </TouchableWithoutFeedback>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "space-around",
    paddingTop: theme.SIZES.BASE * 0.2,
    paddingHorizontal: theme.SIZES.BASE,
    backgroundColor: theme.COLORS.WHITE,
  },
});

export default LoginScreen;
