// src/api/ApiService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

const ApiService = {
    login: (credentials) => axios.post(`${API_URL}/auth/login`, credentials),
    register: (user) => axios.post(`${API_URL}/auth/register`, user),
    getUsers: () => {
        const token = localStorage.getItem('token');
        return axios.get(`${API_URL}/users`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
};

export default ApiService;
