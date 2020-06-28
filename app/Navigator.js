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
import { Icon, Button, Input } from "@ui-kitten/components";
import ContentScreen from "./src/screens/ContentScreen";
import { View } from "react-native";
import NewContent from "./src/screens/NewContent";
import userService from "./src/services/UserService";

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
const SettingsIcon = (props) => (
  <Icon {...props} name="settings-outline" fill="#FFF" />
);

@inject("tokenStore", "generalStore", "userStore")
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

    if (this.props.userStore.getUsername === "") {
      const user = userService.getCurrentUser();
    }
  }

  render() {
    if (!this.props.generalStore.getInitialState) {
      return (
        <NavigationContainer>
          <Stack.Navigator
            initialRouteName="Home"
            screenOptions={{
              headerStyle: {
                backgroundColor: "#2c4770",
              },
              headerTintColor: "#fff",
              headerTitleStyle: {
                fontWeight: "bold",
              },
              headerTitle: null,
            }}
          >
            {this.props.tokenStore.getAccessToken == null ||
            this.props.tokenStore.getAccessToken === "" ? (
              <Stack.Screen name="Login" component={LoginScreen} />
            ) : (
              <Stack.Screen
                name="Home"
                component={HomeScreen}
                options={({ navigation, route }) => ({
                  headerRight: () => (
                    <View style={{ flexDirection: "row" }}>
                      <Button
                        onPress={() => navigation.navigate("Me")}
                        title="Info"
                        appearance="ghost"
                        accessoryLeft={PersonIcon}
                      ></Button>
                      <Button
                        onPress={() => navigation.navigate("Me")}
                        title="Info"
                        appearance="ghost"
                        accessoryLeft={SettingsIcon}
                      ></Button>
                    </View>
                  ),
                })}
              />
            )}
            <Stack.Screen name="Me" component={MeScreen} />
            <Stack.Screen name="Content" component={ContentScreen} />
            <Stack.Screen
              name="NewContent"
              component={NewContent}
              options={({ navigation, route }) => ({
                headerTitle: "New Content",
              })}
            />
          </Stack.Navigator>
        </NavigationContainer>
      );
    } else {
      return <IntroScreen />;
    }
  }
}
