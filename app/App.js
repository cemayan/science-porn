import React from "react";
import { Provider } from "mobx-react";
import scienceContentStore from "./src/store/ScienceContentStore";
import tokenStore from "./src/store/TokenStore";
import generalStore from "./src/store/GeneralStore";
import * as eva from "@eva-design/eva";
import { EvaIconsPack } from "@ui-kitten/eva-icons";
import { SafeAreaView } from "react-native";

import {
  ApplicationProvider,
  Layout,
  Text,
  IconRegistry,
} from "@ui-kitten/components";

import Navigator from "./Navigator";

const stores = {
  scienceContentStore,
  tokenStore,
  generalStore,
};

export default class App extends React.Component {
  render() {
    return (
      <>
        <IconRegistry icons={EvaIconsPack} />
        <ApplicationProvider {...eva} theme={eva.light}>
          <Provider {...stores}>
            <Navigator />
          </Provider>
        </ApplicationProvider>
      </>
    );
  }
}
