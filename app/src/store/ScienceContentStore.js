import { action, observable, computed } from "mobx";

class ScienceContentStore {
  @observable userId = "cem";


  @computed get getUserId() {
    return this.userId;
  }

  @action setUserId(userId) {
    this.userId = userId;
  }
}

const scienceContentStore = new ScienceContentStore();
export default scienceContentStore;
