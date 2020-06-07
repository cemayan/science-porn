import React from "react";
import { View, Text } from "react-native";

const ContentScreen = ({ route, navigation }) => {
  const { content } = route.params;

  return (
    <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
      <Text>{content.title}</Text>
    </View>
  );
};

export default ContentScreen;
