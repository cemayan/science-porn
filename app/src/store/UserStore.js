import { action, observable, computed } from "mobx";

class UserStore {
  @observable email = "";
  @observable id = "";
  @observable profilePicture = "";
  @observable username = "";

  @computed get getEmail() {
    return this.email;
  }

  @action setEmail(email) {
    this.email = email;
  }

  @computed get getId() {
    return this.id;
  }

  @action setId(id) {
    this.id = id;
  }

  @computed get getProfilePicture() {
    return this.profilePicture;
  }

  @action setProfilePicture(profilePicture) {
    this.profilePicture = profilePicture;
  }

  @computed get getUsername() {
    return this.username;
  }

  @action setUsername(username) {
    this.username = username;
  }
}

const userStore = new UserStore();
export default userStore;
