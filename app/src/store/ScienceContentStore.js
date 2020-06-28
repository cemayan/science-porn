import { action, observable, computed } from "mobx";

class ScienceContentStore {
  @observable myContents = [];
  @observable top3 = [];

  @computed get getMyContents() {
    return this.myContents;
  }

  @action setMyContents(myContents) {
    this.myContents = [...myContents];
  }

  @computed get getTop3() {
    return this.top3;
  }

  @action setTop3(top3) {
    this.top3 = [...top3];
  }
}

const scienceContentStore = new ScienceContentStore();
export default scienceContentStore;
