import axios from 'axios';
import {jwtDecode} from 'jwt-decode';

const API_URL = 'http://localhost:8080/api';

const ApiService = {
    login: (credentials) => axios.post(`${API_URL}/auth/login`, credentials),
    register: (user) => axios.post(`${API_URL}/auth/register`, user),
    refreshToken: (refreshToken) => axios.post(`${API_URL}/auth/refresh-token`, { refreshToken }),
    createUser: (user) => {
        const token = localStorage.getItem('token');
        return axios.post(`${API_URL}/users`, user, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    updateUser: (id, user) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/users/${id}`, user, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    deleteUser: (userId) => {
        const token = localStorage.getItem('token');
        return axios.delete(`${API_URL}/users/${userId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
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
    createBook: (book) => {
        const token = localStorage.getItem('token');
        return axios.post(`${API_URL}/books`, book, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    updateBook: (id, book) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/books/${id}`, book, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    deleteBook: (bookId) => {
        const token = localStorage.getItem('token');
        return axios.delete(`${API_URL}/books/${bookId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
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
    createLibrary: (library) => {
        const token = localStorage.getItem('token');
        return axios.post(`${API_URL}/libraries`, library, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
    },
    updateLibrary: (id, library) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/libraries/${id}`, library, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
    },
    deleteLibrary: (id) => {
        const token = localStorage.getItem('token');
        return axios.delete(`${API_URL}/libraries/${id}`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
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
    createPublisher: (publisher) => {
        const token = localStorage.getItem('token');
        return axios.post(`${API_URL}/publishers`, publisher, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    updatePublisher: (id, publisher) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/publishers/${id}`, publisher, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    deletePublisher: (publisherId) => {
        const token = localStorage.getItem('token');
        return axios.delete(`${API_URL}/publishers/${publisherId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    createAuthor: (author) => {
        const token = localStorage.getItem('token');
        return axios.post(`${API_URL}/authors`, author, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
    },
    updateAuthor: (id, author) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/authors/${id}`, author, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
    },
    deleteAuthor: (authorId) => {
        const token = localStorage.getItem('token');
        return axios.delete(`${API_URL}/authors/${authorId}`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
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
                headers: { 'Authorization': `Bearer ${token}` }
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
