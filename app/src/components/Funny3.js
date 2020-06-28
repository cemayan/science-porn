import {
  Button,
  Card,
  Divider,
  Icon,
  Layout,
  ListItem,
  Text,
  ViewPager,
} from "@ui-kitten/components";
import React from "react";
import { StyleSheet } from "react-native";

const InstallButton = (props) => (
  <Button status="success" size="tiny">
    Look
  </Button>
);

const Funny3 = () => {
  return (
    <>
      <Card
        style={{
          alignItems: "center",
          justifyContent: "center",
          backgroundColor: "#EDF1F7",
        }}
      >
        <Text category="h5" style={{ marginVertical: 4 }}>
          <Icon style={styles.icon} fill="#8F9BB3" name="star" /> Funny 5
        </Text>
      </Card>

      <Divider />
    </>
  );
};

const styles = StyleSheet.create({
  layout: {
    flex: 1,
  },
  icon: {
    width: 32,
    height: 32,
  },
  tab: {
    height: 355,
  },
});

export default Funny3;
