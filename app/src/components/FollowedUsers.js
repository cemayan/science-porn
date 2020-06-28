import React from "react";
import { StyleSheet, TouchableHighlight } from "react-native";
import scienceContentService from "../services/ScienceContentService";
import {
  Avatar,
  Button,
  Card,
  IconButton,
  Title,
  Paragraph,
} from "react-native-paper";
import { Layout, Text } from "@ui-kitten/components";
import View from "react-native-web/dist/exports/View";
import { inject, observer } from "mobx-react";
import { toJS } from "mobx";
import { Block } from "galio-framework";
import ContentScreen from "../screens/ContentScreen";

const LeftContent = (props) => <Avatar.Icon {...props} icon="folder" />;

@inject("scienceContentStore")
@observer
export default class FollowedUsers extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      scienceContentList: [],
    };
  }

  async componentDidMount() {
    var content = await scienceContentService.getTop3();
    this.setState({
      scienceContentList: toJS(this.props.scienceContentStore.getTop3),
    });
  }

  render() {
    return (
      <Layout level="3" style={styles.content}>
        {this.state.scienceContentList.map((data) => {
          return (
            <>
              <Card key={data.id} style={{ backgroundColor: "#F7F9FC" }}>
                <Card.Title
                  title={data.userName}
                  subtitle={data.title}
                  left={(props) => (
                    <Avatar.Image
                      {...props}
                      source={{ uri: data.profilePicture }}
                    />
                  )}
                  right={(props) => (
                    <Layout
                      style={{
                        flexDirection: "row",
                        backgroundColor: "#F7F9FC",
                      }}
                    >
                      <IconButton
                        {...props}
                        icon="heart-outline"
                        onPress={() => {}}
                      />
                      <IconButton {...props} icon="share" onPress={() => {}} />
                    </Layout>
                  )}
                />

                <TouchableHighlight
                  onPress={() => {
                    this.props.navigation.navigate("Content", {
                      content: data,
                    });
                  }}
                >
                  <Card.Cover source={{ uri: "https://picsum.photos/700" }} />
                </TouchableHighlight>
              </Card>
              <Text />
            </>
          );
        })}
      </Layout>
    );
  }
}

const styles = StyleSheet.create({
  content: { padding: 10 },
});
