import React from "react";
import { Card, Layout, Text } from "@ui-kitten/components";
import { ScrollView, StyleSheet, View } from "react-native";

const Header = (props) => (
  <View {...props}>
    <Text category="s1">By Wikipedia</Text>
  </View>
);

const HomeCard = (props) => {
  return (
    <React.Fragment>
      <Layout style={styles.topContainer} level="3">
        <Card style={styles.card} header={Header}>
          <Text>With Header</Text>
        </Card>

        <Card style={styles.card} header={Header}>
          <Text>With Header</Text>
        </Card>

        <Card style={styles.card} header={Header}>
          <Text>With Header</Text>
        </Card>
      </Layout>
    </React.Fragment>
  );
};

const styles = StyleSheet.create({
  topContainer: {
    flexDirection: "row",
    justifyContent: "space-between",
    padding: 10,
  },
  card: {
    flex: 1,
    margin: 2,
  },
  footerContainer: {
    flexDirection: "row",
    justifyContent: "flex-end",
  },
  footerControl: {
    marginHorizontal: 1,
  },
});

export default HomeCard;
