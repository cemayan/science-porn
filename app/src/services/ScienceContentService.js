import AsyncStorage from "@react-native-community/async-storage";
import scienceContentStore from "../store/ScienceContentStore";

const scienceContentService = {
  timeDiffCalc: function (dateFuture, dateNow) {
    dateFuture = new Date(
      dateFuture.getTime() - dateFuture.getTimezoneOffset() * 60000
    );

    let diffInMilliSeconds = Math.abs(dateFuture - dateNow) / 1000;

    const days = Math.floor(diffInMilliSeconds / 86400);
    diffInMilliSeconds -= days * 86400;

    const hours = Math.floor(diffInMilliSeconds / 3600) % 24;
    diffInMilliSeconds -= hours * 3600;

    const minutes = Math.floor(diffInMilliSeconds / 60) % 60;
    diffInMilliSeconds -= minutes * 60;

    let difference = "";
    if (days > 0) {
      difference += days === 1 ? `${days} day, ` : `${days} days, `;
    }

    if (hours !== 0) {
      difference +=
        hours === 0 || hours === 1 ? `${hours} hour, ` : `${hours} hours, `;
    }

    difference +=
      minutes === 0 || hours === 1
        ? `${minutes} minutes`
        : `${minutes} minutes`;

    return difference;
  },
  getMyContents: async () => {
    const token = await AsyncStorage.getItem("accessToken");

    var contentList = await fetch(
      "http://localhost:8082/content/getMyContent",
      {
        method: "POST",
        headers: {
          Authorization: "Bearer " + token,
        },
      }
    )
      .then((x) => x.json())
      .then(async (resp) => {
        let arr = [];

        if (resp.length > 0) {
          resp.forEach((data) => {
            arr.push({
              userId: data.person.userId,
              image: data.scienceContent.image,
              avatar: data.person.profilePicture,
              title: data.scienceContent.title,
              caption: scienceContentService
                .timeDiffCalc(
                  new Date(new Date().toISOString()),
                  new Date(data.scienceContent.createdAt)
                )
                .toString(),
              content: data.scienceContent.content,
              location: "Kadıköy",
            });
          });
        }

        await scienceContentStore.setMyContents(arr);
      })
      .catch((err) => {});

    return contentList;
  },
  getTop5: async () => {
    const token = await AsyncStorage.getItem("accessToken");

    var contentList = await fetch("http://localhost:8082/content/getTop5", {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((x) => x.json())
      .then(async (resp) => {
        let arr = [];

        if (resp.length > 0) {
          resp.forEach((data) => {
            arr.push({
              id: data.id,
              userId: data.userId,
              title: data.title,
              content: data.content,
              profilePicture: data.authorBy.person.profilePicture,
              userName: data.authorBy.person.userName,
            });
          });
        }

        await scienceContentStore.setTop5(arr);
      });
  },
};

export default scienceContentService;
