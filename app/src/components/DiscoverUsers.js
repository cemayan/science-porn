import React from "react";
import { StyleSheet, View, Dimensions } from "react-native";
import { Button, Card, Layout, Modal, Text } from "@ui-kitten/components";

class DiscoverUsers extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: this.props.discoverUsersModalVisible,
    };
  }

  setVisible(status) {
    this.setState({ visible: status });
    this.props.setModalStatus(status);
  }

  componentDidUpdate(prevProps, prevState) {
    if (
      this.props.discoverUsersModalVisible !==
      prevProps.discoverUsersModalVisible
    ) {
      this.setState({ visible: this.props.discoverUsersModalVisible });
    }
  }

  render() {
    return (
      <Modal
        style={{
          minHeight: 192,
          width: Dimensions.get("window").width - 70,
        }}
        visible={this.state.visible}
        backdropStyle={styles.backdrop}
        onBackdropPress={() => this.setVisible(false)}
      >
        <Card disabled={true}>
          <Layout style={styles.container}></Layout>
        </Card>
      </Modal>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    minHeight: 192,
  },
  backdrop: {
    backgroundColor: "rgba(0, 0, 0, 0.5)",
  },
});

export default DiscoverUsers;
