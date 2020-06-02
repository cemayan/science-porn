import { action, observable, computed } from "mobx";

class GeneralStore {
  @observable initialState = true;

  @computed get getInitialState() {
    return this.initialState;
  }

  @action setInitialState(initialState) {
    this.initialState = initialState;
  }
}

const generalStore = new GeneralStore();
export default generalStore;
