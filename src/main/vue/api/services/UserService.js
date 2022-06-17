import axios from 'axios';

const API_URL = '/api';
export let requestData = {
    data: null,
    error: false,
    statusCode: 0
}
class UserService {
    getUser() {
        return axios.get(API_URL + "/player/all");
    }

    getUserById(id) {
        return axios.get(API_URL + "/player/find/" + id);
    }

    deleteUserById(id) {
        return axios.delete(API_URL + "/player/delete/" + id);
    }
    updateUser(id, data) {
        console.log(data)
        return axios.put(API_URL +"/player/update", data);
    }
    /**
     * This Method signup User data  from Api POST request ('/user/signup').
     *
     * @param data where to signup the User
     * @returns {Promise<{data: null, error: boolean,statusCode: number}>}requested Data in data where the requested Data is,
     * when error boolean is false then signup Request where correctly send.
     * Or when error boolean is true there is the ErrorHandling message inside data.
     */
    async signUpUser(data) {
        let request = axios.post(API_URL + "/player/add", data);
        await request.then(async function (response) {
            //console.log(response.data)
            requestData.data = response.data;
            requestData.statusCode = response.status;
        }).catch(function (error) {
           console.log(error)
        })
        return requestData;
    }

    /**
     * This Method searches a User from the DB  via Api POST request ('/user/findEmail').
     *
     * @param data where to signup the User
     * @returns {Promise<{data: null, error: boolean,statusCode: number}>}requested Data in data where the requested Data is,
     * when error boolean is false then findEmail Request where correctly send.
     * Or when error boolean is true there is the ErrorHandling message inside data.
     */

    async getUserByEmail(email) {
        let request = axios.get(API_URL + "/player/findEmail/" + email);
        await request.then(async function (response) {
            requestData.data = response.data
            requestData.statusCode = response.status;
        }).catch(function (error) {
           // requestData = getErrorStatusText(error, requestData)
        })
        return requestData
    }
}

export default new UserService();
