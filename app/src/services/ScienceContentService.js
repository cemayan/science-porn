import AsyncStorage from '@react-native-community/async-storage';
import ScienceContentStore from "../store/ScienceContentStore";

 const scienceContentService =  {

     timeDiffCalc: function(dateFuture, dateNow) {


            dateFuture = new Date(dateFuture.getTime() - dateFuture.getTimezoneOffset()*60000)

            let diffInMilliSeconds = Math.abs(dateFuture - dateNow) / 1000;

            // calculate days
            const days = Math.floor(diffInMilliSeconds / 86400);
            diffInMilliSeconds -= days * 86400;
            // calculate hours
            const hours = Math.floor(diffInMilliSeconds / 3600) % 24;
            diffInMilliSeconds -= hours * 3600;
            // calculate minutes
            const minutes = Math.floor(diffInMilliSeconds / 60) % 60;
            diffInMilliSeconds -= minutes * 60;

            let difference = '';
            if (days > 0) {
                difference += (days === 1) ? `${days} day, ` : `${days} days, `;
            }

            if(hours !== 0 ){
                difference += (hours === 0 || hours === 1) ? `${hours} hour, ` : `${hours} hours, `;
            }

            difference += (minutes === 0 || hours === 1) ? `${minutes} minutes` : `${minutes} minutes`;

            return difference;
    },
    getContents: async () => {

       var token = await  AsyncStorage.getItem("access_token");

       var contentList = await fetch('http://localhost:8082/content/getContent',{
           method: 'POST',
           headers: {
               'Authorization': 'Bearer ' + token,
           }
       }).then(x => x.json()).then(async (resp) => {

           let arr = [];

           if(resp.length > 0 ) {

               resp.forEach((data) => {
                   arr.push(
                       {
                           "userId": data.person.userId,
                           "image": data.scienceContent.image,
                           "avatar": data.person.profilePicture,
                           "title": data.scienceContent.title,
                           "caption": scienceContentService.timeDiffCalc(new Date(new Date().toISOString()), new Date(data.scienceContent.createdAt)).toString(),
                           "content": data.scienceContent.content,
                           "location": "Kadıköy"

                       }
                   )
               })
           }

          await ScienceContentStore.setContents(arr);
       }).catch((err) =>  {

       })

        return contentList;
    }
}

export default scienceContentService;
