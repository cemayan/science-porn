import React from "react";
import { Provider } from "mobx-react";
import scienceContentStore from "./src/store/ScienceContentStore";
import tokenStore from "./src/store/TokenStore";

import Navigator from "./Navigator";

const stores = {
  scienceContentStore,
  tokenStore,
};

export default class App extends React.Component {
  render() {
    return (
      <Provider {...stores}>
        <Navigator />
      </Provider>
    );
  }
}
