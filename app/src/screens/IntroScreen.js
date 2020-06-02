import React from "react";
import AppIntroSlider from "react-native-app-intro-slider";
import {
  View,
  Text,
  Image,
  StyleSheet,
  Dimensions,
  ImageBackground,
} from "react-native";
import HomeScreen from "./HomeScreen";
import LoginScreen from "./LoginScreen";
import AsyncStorage from "@react-native-community/async-storage";
import { inject, observer } from "mobx-react";

const dimensions = Dimensions.get("window");
const c = Math.round((dimensions.width * 9) / 16);
const imageWidth = dimensions.width;

const slides = [
  {
    key: "1",
    title: "Title 1",
    text: "Description.\nSay something cool",
    image: require("../../assets/img1.jpeg"),
    backgroundColor: "#59b2ab",
  },
  {
    key: "2",
    title: "Title 2",
    text: "Other cool stuff",
    image: require("../../assets/img2.jpeg"),
    backgroundColor: "#febe29",
  },
];

const styles = StyleSheet.create({
  slide: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "blue",
    resizeMode: "cover",
  },
  image: {
    width: 300,
    height: 400,
    marginVertical: 32,
  },
  text: {
    color: "rgba(255, 255, 255, 0.8)",
    textAlign: "center",
  },
  title: {
    fontSize: 22,
    color: "white",
    textAlign: "center",
  },
});

@inject("generalStore")
@observer
export default class IntroScreen extends React.Component {
  constructor() {
    super();
    this.state = {
      showRealApp: false,
    };
  }

  _renderItem = ({ item }) => {
    return (
      <ImageBackground style={styles.slide} source={item.image}>
        <Text style={styles.text}>{item.text}</Text>
      </ImageBackground>
    );
  };
  _onDone = () => {
    this.props.generalStore.setInitialState(false);
    AsyncStorage.setItem("initialState", "false");
    this.setState({ showRealApp: true });
  };
  render() {
    if (this.state.showRealApp) {
      return <LoginScreen />;
    } else {
      return (
        <AppIntroSlider
          renderItem={this._renderItem}
          data={slides}
          onDone={this._onDone}
        />
      );
    }
  }
}
