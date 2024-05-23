import axios from 'axios';
import { jwtDecode } from 'jwt-decode';

const API_URL = 'http://localhost:8080/api';

const ApiService = {
    login: (credentials) => axios.post(`${API_URL}/auth/login`, credentials),
    register: (user) => axios.post(`${API_URL}/auth/register`, user),
    refreshToken: (refreshToken) => axios.post(`${API_URL}/auth/refresh-token`, { refreshToken }),
    getUsers: async () => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if (token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/users`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        } else {
            throw new Error('No token or refresh token available');
        }
    },
    getBooks: async () => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if (token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/books`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        }
    },
    getLibraries: async () => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if (token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/libraries`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        }
    },
    getPublishers: async () => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if (token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/publishers`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        }
    },
    getAuthors: async () => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if (token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/authors`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        }
    }
};

const isTokenExpired = (token) => {
    const decoded = jwtDecode(token);
    const currentTime = Date.now() / 1000;
    return decoded.exp < currentTime;
};

export default ApiService;
