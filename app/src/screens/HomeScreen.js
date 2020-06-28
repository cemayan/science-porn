import React from "react";
import {
  View,
  StyleSheet,
  ScrollView,
  Text,
  Button,
  TouchableWithoutFeedback,
} from "react-native";
import * as eva from "@eva-design/eva";
import { ViewPager, Layout, Divider } from "@ui-kitten/components";
import Top3 from "../components/Top3";
import Funny3 from "../components/Funny3";
import HomeCard from "../components/HomeCard";
import FollowedUsers from "../components/FollowedUsers";
import { SearchBox } from "../components/SearchBox";
import ContentCarousel from "../components/ContentCarousel";
import NewContent from "../components/DiscoverUsers";

export default class HomeScreen extends React.Component {
  constructor(props) {
    super(props);
    this.inputRef = React.createRef();
    this.state = {
      selectedIndex: 0,
    };
  }

  bs = React.createRef();

  render() {
    return (
      <ScrollView>
        <Layout level="3" style={styles.searchBox}>
          <SearchBox {...this.props} />
        </Layout>
        <ViewPager
          style={styles.viewPager}
          selectedIndex={this.state.selectedIndex}
          onSelect={(index) => this.setState({ selectedIndex: index })}
        >
          <Layout style={styles.tab} level="3">
            <Top3 {...this.props} />
          </Layout>
          <Layout style={styles.tab} level="3">
            <Funny3 {...this.props} />
          </Layout>
        </ViewPager>

        <Layout style={styles.carousel} level="3">
          <ContentCarousel />
        </Layout>

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
