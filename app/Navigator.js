import React from "react";
import { inject, observer } from "mobx-react";
import { createStackNavigator } from "@react-navigation/stack";
import AsyncStorage from "@react-native-community/async-storage";
import IntroScreen from "./src/screens/IntroScreen";
import LoginScreen from "./src/screens/LoginScreen";
import HomeScreen from "./src/screens/HomeScreen";
import { NavigationContainer } from "@react-navigation/native";
import tokenStore from "./src/store/TokenStore";

const Stack = createStackNavigator();

const getState = async () => {
  return await AsyncStorage.getItem("initialState");
};

const getToken = async () => {
  return await AsyncStorage.getItem("access_token");
};

@inject("tokenStore")
@observer
export default class Navigator extends React.Component {
  constructor() {
    super();
  }

  render() {
    if (getState == null) {
      return <IntroScreen />;
    }

    return (
      <NavigationContainer>
        <Stack.Navigator initialRouteName="Home">
          {this.props.tokenStore.getAccessToken == "" ? (
            <Stack.Screen name="Login" component={LoginScreen} />
          ) : (
            <Stack.Screen name="Home" component={HomeScreen} />
          )}
        </Stack.Navigator>
      </NavigationContainer>
    );
  }
}
