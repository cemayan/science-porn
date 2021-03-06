import {
  Button,
  Card,
  Divider,
  Icon,
  Layout,
  ListItem,
  Text,
  Avatar,
  ViewPager,
} from "@ui-kitten/components";
import React from "react";
import { StyleSheet } from "react-native";
import { inject, observer } from "mobx-react";
import scienceContentService from "../services/ScienceContentService";
import { toJS } from "mobx";

const InstallButton = (props) => (
  <Button
    status="success"
    size="tiny"
    onPress={() => {
      props.navigation.navigate("Content", {
        content: props.content,
      });
    }}
  >
    Look
  </Button>
);

const ItemImage = (props) => (
  <Avatar
    {...props}
    style={[props.style, { tintColor: null }]}
    source={{ uri: props.image }}
  />
);

@inject("scienceContentStore")
@observer
export default class Top3 extends React.Component {
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
      <>
        <Card
          style={{
            alignItems: "center",
            justifyContent: "center",
            backgroundColor: "#EDF1F7",
          }}
        >
          <Text category="h5" style={{ marginVertical: 1 }}>
            <Icon style={styles.icon} fill="#8F9BB3" name="star" /> Top 3
          </Text>
        </Card>
        <Layout style={styles.layout}>
          {this.state.scienceContentList.map((data) => {
            return (
              <Layout>
                <ListItem
                  style={{ backgroundColor: "#F7F9FC" }}
                  key={data.id}
                  title={data.userName}
                  description={data.title}
                  accessoryLeft={() => (
                    <ItemImage image={data.profilePicture} />
                  )}
                  accessoryRight={() => (
                    <InstallButton {...this.props} content={data} />
                  )}
                />
                <Divider />
              </Layout>
            );
          })}
        </Layout>
      </>
    );
  }
}

const styles = StyleSheet.create({
  icon: {
    width: 32,
    height: 32,
  },
});
