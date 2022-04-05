import axios from 'axios';

const API_URL = 'http://localhost:8080';
class PlayerService {
    getPlayer() {
        return axios.get(API_URL+"/player/all");
    }
    get(id) {
        return axios.get(`/tutorials/${id}`);
    }
    addPlayer(data) {
        return axios.post("/player/add", data);
    }
    updatePlayer(id, data) {
        return axios.put(`/player/update`, data);
    }
    deletePlayer(id) {
        return axios.delete(`/player/delete/${id}`);
    }
    deleteAll() {
        return axios.delete(`/tutorials`);
    }
    findByTitle(title) {
        return axios.get(`/tutorials?title=${title}`);
    }
}
export default new PlayerService();