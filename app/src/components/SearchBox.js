import React from "react";
import { Input, Icon } from "@ui-kitten/components";

const renderIcon = (props) => <Icon {...props} name="search" />;

export const SearchBox = () => {
  const [value, setValue] = React.useState("");

  return (
    <Input
      placeholder="Search user,content..."
      value={value}
      accessoryRight={renderIcon}
      onChangeText={(nextValue) => setValue(nextValue)}
    />
  );
};
