import React from "react";
import { View, StyleSheet, ScrollView } from "react-native";
import * as eva from "@eva-design/eva";
import { ViewPager, Layout, Divider } from "@ui-kitten/components";
import Top5 from "../components/Top5";
import Funny5 from "../components/Funny5";
import HomeCard from "../components/HomeCard";

export default class HomeScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedIndex: 0,
    };
  }

  render() {
    return (
      <ScrollView>
        <ViewPager
          selectedIndex={this.state.selectedIndex}
          onSelect={(index) => this.setState({ selectedIndex: index })}
        >
          <Layout style={styles.tab} level="3">
            <Top5 {...this.props} />
          </Layout>
          <Layout style={styles.tab} level="3">
            <Funny5 {...this.props} />
          </Layout>
        </ViewPager>

        <HomeCard />
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  tab: {},
});
