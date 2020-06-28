import React, { Component } from "react";
import {
  StyleSheet,
  TextInput,
  ScrollView,
  TouchableHighlight,
  Alert,
} from "react-native";
import { Layout, Input, Text, Button, Divider } from "@ui-kitten/components";
import { Avatar, Card, IconButton } from "react-native-paper";
import userService from "../services/UserService";
import { inject, observer } from "mobx-react";
import scienceContentService from "../services/ScienceContentService";

@inject("userStore")
@observer
export default class NewContent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      title: "",
      content: "",
      image: "",
      buttonStatus: false,
    };

    this.handleChange = this.handleChange.bind(this);
    this.shareContentClick = this.shareContentClick.bind(this);
  }

  shareContentClick(event) {
    const self = this;
    self.setState({ buttonStatus: true });

    if (this.state.title === "" || this.state.content === "") {
      Alert.alert(
        "Warning",
        "Title and Content cannot be null!",
        [
          {
            text: "Try again",
            onPress: () => console.log("Title and Content cannot be null!"),
          },
          {
            text: "Cancel",
            onPress: () => console.log("Cancel Pressed"),
            style: "cancel",
          },
        ],
        { cancelable: false }
      );

      self.setState({ buttonStatus: false });
    } else {
      const obj = {
        title: this.state.title,
        content: this.state.content,
        userId: this.props.userStore.getId,
        image: this.state.image,
        relationship: "AUTHOR_BY",
      };

      scienceContentService.shareContent(obj).then((msg) => {
        self.setState({ buttonStatus: false });
        this.state.title = "";
        this.state.content = "";
        this.state.image = "";
      });
    }
  }

  handleChange(event, stateName) {
    this.setState({ [stateName]: event.nativeEvent.text });
  }

  render() {
    return (
      <ScrollView>
        <Layout level="3" style={styles.content}>
          <Input
            placeholder="Title"
            value={this.state.title}
            onChange={(event) => this.handleChange(event, "title")}
          />
          <Input
            maxHeight={200}
            multiline={true}
            textStyle={{ minHeight: 250 }}
            placeholder="Content"
            value={this.state.content}
            onChange={(event) => this.handleChange(event, "content")}
          />

          <Input
            placeholder="https://"
            value={this.state.image}
            onChange={(event) => this.handleChange(event, "image")}
          />

          <Button
            style={styles.button}
            status="success"
            disabled={this.state.buttonStatus}
            onPress={this.shareContentClick}
          >
            Share
          </Button>
        </Layout>
        <Layout level="3" style={styles.content}>
          <Divider></Divider>

          <Card style={{ backgroundColor: "#F7F9FC" }}>
            <Card.Title
              title={this.props.userStore.getUsername}
              subtitle={this.state.title}
              left={(props) => (
                <Avatar.Image
                  {...props}
                  source={{ uri: this.props.userStore.getProfilePicture }}
                />
              )}
            />
            {this.state.image === "" ? (
              <></>
            ) : (
              <Card.Cover source={{ uri: this.state.image }} />
            )}
            <Text style={{ padding: 20 }}>{this.state.content}</Text>
          </Card>
        </Layout>
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  content: { padding: 10 },
});
