import React from "react";
import { View, Text } from "react-native";

const ContentScreen = ({ route, navigation }) => {
  const { title } = route.params;

  return (
    <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
      <Text>{title}</Text>
    </View>
  );
};

export default ContentScreen;
