import React from "react";
import Carousel from "react-native-snap-carousel";
import {
  Platform,
  View,
  ScrollView,
  Text,
  StatusBar,
  SafeAreaView,
} from "react-native";
import SliderEntry from "../components/SliderEntry";
import styles, { colors } from "../styles/index.style";
import { ENTRIES1, ENTRIES2 } from "../static/entries";
import { sliderWidth, itemWidth } from "../styles/SliderEntry.style";
import { Layout } from "@ui-kitten/components";

export default class ContentCarousel extends React.Component {
  constructor(props) {
    super(props);
  }
  _renderItem({ item, index }) {
    return <SliderEntry data={item} even={(index + 1) % 2 === 0} />;
  }

  _renderLightItem({ item, index }) {
    return <SliderEntry data={item} even={false} />;
  }

  _renderDarkItem({ item, index }) {
    return <SliderEntry data={item} even={true} />;
  }

  render() {
    const isTinder = false;
    return (
      <Layout style={styles.carousel} level="3">
        <SafeAreaView style={{ flex: 1, paddingTop: 50 }}>
          <View>
            <Text style={[styles.title, styles.titleDark]}>
              Scienceporn Useful Contents
            </Text>
            <Carousel
              data={isTinder ? ENTRIES2 : ENTRIES1}
              renderItem={this._renderItem}
              sliderWidth={sliderWidth}
              itemWidth={itemWidth}
              containerCustomStyle={styles.slider}
              contentContainerCustomStyle={styles.sliderContentContainer}
              layout="stack"
              loop={true}
            />
          </View>
        </SafeAreaView>
      </Layout>
    );
  }
}
