import React, { useRef } from "react";
import { Input, Icon, Button } from "@ui-kitten/components";
import { ScrollView, View } from "react-native";
import DiscoverUsers from "./DiscoverUsers";

const renderIcon = (props) => <Icon {...props} name="search" />;

const AddIcon = (props) => (
  <Icon
    {...props}
    name="plus-square-outline"
    style={{ width: 30, height: 25 }}
  />
);

const PersonAddIconIcon = (props) => (
  <Icon
    {...props}
    name="person-add-outline"
    style={{ width: 30, height: 25 }}
  />
);

export const SearchBox = (props) => {
  const [value, setValue] = React.useState("");
  const [discoverUsersModalVisible, setModalStatus] = React.useState(false);

  return (
    <>
      <View style={{ flexDirection: "row", flex: 1 }}>
        <View style={{ flex: 0.8 }}>
          <Input
            placeholder="Search user,content..."
            value={value}
            accessoryRight={renderIcon}
            onChangeText={(nextValue) => setValue(nextValue)}
          />
        </View>
        <View style={{ flex: 0.2, flexDirection: "row", paddingRight: 20 }}>
          <Button
            appearance="ghost"
            accessoryLeft={AddIcon}
            onPress={() => props.navigation.navigate("NewContent")}
          ></Button>
          <Button
            appearance="ghost"
            accessoryLeft={PersonAddIconIcon}
            onPress={() => setModalStatus(true)}
          ></Button>
        </View>

        <DiscoverUsers
          discoverUsersModalVisible={discoverUsersModalVisible}
          setModalStatus={setModalStatus}
        />
      </View>
    </>
  );
};
