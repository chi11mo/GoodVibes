import axios from 'axios';

const API_URL = '/api';


class ClubService {
    getClubs() {
        return axios.get(API_URL + "/club/all");
    }
    getClubById(id){
        return axios.get(API_URL + "/club/find"+id);
    }
    registerGame(data){
        return axios.post(API_URL + "/club/addGame",data)
    }


}

export default new ClubService();
