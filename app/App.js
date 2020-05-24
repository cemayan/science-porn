import React from "react";
import { View, StatusBar, SafeAreaView } from "react-native";
import HomeScreen from "./src/screens/HomeScreen";
import { Provider } from "mobx-react";
import scienceContentStore from "./src/store/ScienceContentStore";

export default class App extends React.Component {
  render() {
    return (
      <Provider scienceContentStore={scienceContentStore}>
        <SafeAreaView style={{ flex: 1 }}>
          <StatusBar hidden={false} />
          <HomeScreen />
        </SafeAreaView>
      </Provider>
    );
  }
}
