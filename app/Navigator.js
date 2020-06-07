import React from "react";
import { inject, observer } from "mobx-react";
import { createStackNavigator } from "@react-navigation/stack";
import AsyncStorage from "@react-native-community/async-storage";
import IntroScreen from "./src/screens/IntroScreen";
import LoginScreen from "./src/screens/LoginScreen";
import HomeScreen from "./src/screens/HomeScreen";
import { NavigationContainer } from "@react-navigation/native";
import tokenStore from "./src/store/TokenStore";
import generalStore from "./src/store/GeneralStore";
import MeScreen from "./src/screens/MeScreen";
import { SafeAreaView } from "react-native";
import { Icon, Button } from "@ui-kitten/components";
import ContentScreen from "./src/screens/ContentScreen";

const Stack = createStackNavigator();

const getState = async () => {
  return await AsyncStorage.getItem("initialState");
};

const getToken = async () => {
  return await AsyncStorage.getItem("access_token");
};

const PersonIcon = (props) => (
  <Icon {...props} name="person-outline" fill="#FFF" />
);

@inject("tokenStore", "generalStore")
@observer
export default class Navigator extends React.Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    //AsyncStorage.removeItem("accessToken");
    AsyncStorage.getItem("accessToken").then((data) => {
      if (data != null || data !== "") {
        this.props.tokenStore.setAccessToken(data);
      }
    });
    AsyncStorage.getItem("initialState").then((data) => {
      if (data === "false") {
        this.props.generalStore.setInitialState(false);
      }
    });
  }

  render() {
    if (!this.props.generalStore.getInitialState) {
      return (
        <NavigationContainer>
          <Stack.Navigator initialRouteName="Home">
            {this.props.tokenStore.getAccessToken == null ||
            this.props.tokenStore.getAccessToken === "" ? (
              <Stack.Screen name="Login" component={LoginScreen} />
            ) : (
              <Stack.Screen
                name="Home"
                component={HomeScreen}
                options={({ navigation, route }) => ({
                  headerStyle: {
                    backgroundColor: "#2c4770",
                  },
                  headerTintColor: "#fff",
                  headerTitleStyle: {
                    fontWeight: "bold",
                  },
                  headerRight: () => (
                    <Button
                      onPress={() => navigation.navigate("Me")}
                      title="Info"
                      appearance="ghost"
                      accessoryLeft={PersonIcon}
                    ></Button>
                  ),
                })}
              />
            )}
            <Stack.Screen name="Me" component={MeScreen} />
            <Stack.Screen name="Content" component={ContentScreen} />
          </Stack.Navigator>
        </NavigationContainer>
      );
    } else {
      return <IntroScreen />;
    }
  }
}
