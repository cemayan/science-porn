import React from "react";
import { observer, inject } from "mobx-react";
import { toJS } from "mobx";

import { ScrollView, StyleSheet, Dimensions, SafeAreaView } from "react-native";
import { LinearGradient } from "expo-linear-gradient";
import scienceContentService from "../services/ScienceContentService";

// galio components
import { Block, Card, Text, Icon, NavBar } from "galio-framework";
import theme from "../theme";

import Constants from "expo-constants";
import scienceContentStore from "../store/ScienceContentStore";

const { statusBarHeight } = Constants;

const { width, height } = Dimensions.get("screen");

@inject("scienceContentStore")
@observer
export default class MeScreen extends React.Component {
  constructor() {
    super();

    this.state = {
      scienceContentList: [],
    };
  }

  async componentDidMount() {
    var content = await scienceContentService.getMyContents();
    this.setState({
      scienceContentList: toJS(this.props.scienceContentStore.getMyContents),
    });
  }

  render() {
    return (
      <>
        <SafeAreaView>
          <ScrollView contentContainerStyle={styles.cards}>
            <Block flex space="between">
              {this.state.scienceContentList &&
                this.state.scienceContentList.map((content, id) => (
                  <Card
                    key={`card-${content.image}`}
                    flex
                    borderless
                    shadowColor={theme.COLORS.BLACK}
                    titleColor={content.full ? theme.COLORS.WHITE : null}
                    style={styles.card}
                    title={content.title}
                    caption={content.caption}
                    avatar={`${content.avatar}?${id}`}
                    image={content.image}
                    location={content.location}
                    imageStyle={[content.padded ? styles.rounded : null]}
                    imageBlockStyle={[
                      content.padded ? { padding: theme.SIZES.BASE / 2 } : null,
                      content.full ? null : styles.noRadius,
                    ]}
                    footerStyle={content.full ? styles.full : null}
                  >
                    {content.full ? (
                      <LinearGradient
                        colors={["transparent", "rgba(0,0,0, 0.8)"]}
                        style={styles.gradient}
                      />
                    ) : null}
                  </Card>
                ))}
            </Block>
          </ScrollView>
        </SafeAreaView>
      </>
    );
  }
}

const styles = StyleSheet.create({
  cards: {
    width,
    backgroundColor: theme.COLORS.WHITE,
    alignItems: "center",
    justifyContent: "flex-start",
  },
  card: {
    backgroundColor: theme.COLORS.WHITE,
    width: width - theme.SIZES.BASE * 2,
    marginVertical: theme.SIZES.BASE * 0.875,
    elevation: theme.SIZES.BASE / 2,
  },
  full: {
    position: "absolute",
    bottom: 0,
    right: 0,
    left: 0,
  },
  noRadius: {
    borderBottomLeftRadius: 0,
    borderBottomRightRadius: 0,
  },
  rounded: {
    borderRadius: theme.SIZES.BASE * 0.1875,
  },
  gradient: {
    bottom: 0,
    left: 0,
    right: 0,
    height: 90,
    position: "absolute",
    overflow: "hidden",
    borderBottomRightRadius: theme.SIZES.BASE * 0.5,
    borderBottomLeftRadius: theme.SIZES.BASE * 0.5,
  },
});
