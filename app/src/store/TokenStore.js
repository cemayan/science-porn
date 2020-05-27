import { action, observable, computed } from "mobx";

class TokenStore {
  @observable accessToken = "";

  @computed get getAccessToken() {
    return this.accessToken;
  }

  @action setAccessToken(accessToken) {
    this.accessToken = accessToken;
  }
}

const tokenStore = new TokenStore();
export default tokenStore;
