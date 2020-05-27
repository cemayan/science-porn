import { action, observable, computed } from "mobx";

class ScienceContentStore {
  @observable contents = [];

  @computed get getContents() {
    return this.contents;
  }

  @action setContents(contents) {
    this.contents = [...contents];
  }

}

const scienceContentStore = new ScienceContentStore();
export default scienceContentStore;
