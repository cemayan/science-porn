import React from "react";
import { View, StyleSheet, ScrollView } from "react-native";
import * as eva from "@eva-design/eva";
import { ViewPager, Layout, Divider } from "@ui-kitten/components";
import Top5 from "../components/Top5";
import Funny5 from "../components/Funny5";
import HomeCard from "../components/HomeCard";
import FollowedUsers from "../components/FollowedUsers";
import { SearchBox } from "../components/SearchBox";
import ContentCarousel from "../components/ContentCarousel";

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
        <Layout level="3" style={styles.searchBox}>
          <SearchBox />
        </Layout>
        <ViewPager
          style={styles.viewPager}
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

        <Layout style={styles.carousel} level="3">
          <ContentCarousel />
        </Layout>
        <HomeCard />

        <FollowedUsers {...this.props} />
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  tab: {},
  searchBox: { padding: 10 },
  viewPager: { paddingBottom: 10 },
  carousel: { paddingTop: 8 },
});
