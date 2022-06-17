import axios from 'axios';

const API_URL = '/api';

class GameService {
    getGames() {
        return axios.get(API_URL + "/game/all");
    }

    getGameById(id) {
        return axios.get(API_URL + "/game/find/" + id);
    }

    deleteGameById(id) {
        return axios.delete(API_URL + "/game/delete/" + id);
    }
    updateGame(id, data) {
        console.log(data)
        return axios.put(API_URL +"/game/update", data);
    }
    registerGame(data){
        console.log(data)
        return axios.post(API_URL +"/game/add", data);
    }
}

export default new GameService();
