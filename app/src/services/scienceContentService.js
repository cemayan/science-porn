
const scienceContentService =  {

    getContents: () => {
        return fetch('http://')
            .then(x => x.json())
    }
}

export default scienceContentService;
