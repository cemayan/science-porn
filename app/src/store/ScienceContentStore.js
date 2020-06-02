import { action, observable, computed } from "mobx";

class ScienceContentStore {
  @observable myContents = [];
  @observable top5 = [];

  @computed get getMyContents() {
    return this.myContents;
  }

  @action setMyContents(myContents) {
    this.myContents = [...myContents];
  }

  @computed get getTop5() {
    return this.top5;
  }

  @action setTop5(top5) {
    this.top5 = [...top5];
  }
}

const scienceContentStore = new ScienceContentStore();
export default scienceContentStore;
