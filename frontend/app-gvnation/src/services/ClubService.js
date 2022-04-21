import axios from 'axios';

const API_URL = 'http://localhost:8080';
class ClubService {
    getClubs() {
        return axios.get(API_URL+"/club/all");
    }
    get(id) {
        return axios.get(`/tutorials/${id}`);
    }
    addClub(data) {
        return axios.post("/club/add", data);
    }
    updateClub(id, data) {
        return axios.put(`/club/update`, data);
    }
    deleteClub(id) {
        return axios.delete(`/club/delete/${id}`);
    }
    deleteAll() {
        return axios.delete(`/tutorials`);
    }
    findByTitle(title) {
        return axios.get(`/tutorials?title=${title}`);
    }
}
export default new ClubService();