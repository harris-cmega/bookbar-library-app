import axios from 'axios';
import {jwtDecode} from 'jwt-decode';

const API_URL = 'http://localhost:8080/api';

const ApiService = {
    login: (credentials) => axios.post(`${API_URL}/auth/login`, credentials),
    register: (user) => axios.post(`${API_URL}/auth/register`, user),
    refreshToken: (refreshToken) => axios.post(`${API_URL}/auth/refresh-token`, { refreshToken }),
    createUser: async (user) => {
        const token = localStorage.getItem('token');
        try {
            // Create the user
            const response = await axios.post(`${API_URL}/users`, user, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            // Extract the user ID from the response
            const userId = response.data.id;

            // Create a cart for the new user
            await axios.post(`${API_URL}/carts`, { userId }, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            return response;
        } catch (error) {
            console.error('Failed to create user and cart:', error);
            throw error;
        }
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
    getPublicBooks: async (page = 0, size = 10) => {
        return axios.get(`${API_URL}/public/books`, {params: { page, size }});
    },
    getPublicBookById: async (id) => {
        return axios.get(`${API_URL}/public/books/${id}`, {
        });
    },


    getPublicCategories: async () => {
        return axios.get(`${API_URL}/public/categories`);
    },

    getPublicAuthors: async () => {
        return axios.get(`${API_URL}/public/authors`);
    },

    searchPublicBooks: async (query) => {
        try {
            const response = await axios.get(`${API_URL}/public/books/search`, { params: { query } });
            return response.data;
        } catch (error) {
            console.error('Error searching books:', error);
            throw error;
        }
    },

    getBooks: async (page = 0, size = 10) => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if (token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/books`, {
                params: { page, size },
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        }
    },
    getBookById: async (id) => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if (token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/books/${id}`, {
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
                'Authorization': `Bearer ${token}`,
            }
        });
    },
    updateBook: (id, book) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/books/${id}`, book, {
            headers: {
                'Authorization': `Bearer ${token}`,
            }
        });
    },
    uploadBookImage: (imageFile) => {
        const token = localStorage.getItem('token');
        const formData = new FormData();
        formData.append('image', imageFile);
        return axios.post(`${API_URL}/books/upload-image`, formData, {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'multipart/form-data'
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
    searchBooks: async (query) => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if (token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/books/search`, {
                params: { query },
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
    },
    getBookFiles: async () => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if (token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/book-files`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        }
    },  
    createBookFile: (bookFile) => {
        const token = localStorage.getItem('token');
        return axios.post(`${API_URL}/book-files`, bookFile, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
    },
    updateBookFile: async (id, bookFile) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/book-files/${id}`, bookFile, {
            headers: { 'Authorization': `Bearer ${token}`}
        })
      },
    deleteBookFile: (bookFileId) => {
        const token = localStorage.getItem('token');
        return axios.delete(`${API_URL}/book-files/${bookFileId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    uploadBookFileFile: (bookfileFile) => {
        const token = localStorage.getItem('token');
        const formData = new FormData();
        formData.append('file', bookfileFile);
        return axios.post(`${API_URL}/book-files/upload-book-file`, formData, {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'multipart/form-data'
            }
        });
    },
    
    getUserSubscriptions: async () => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if(token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/user-subscriptions`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        }
    },
    createUserSubscription: (userSubscription) => {
        const token = localStorage.getItem('token');
        return axios.post(`${API_URL}/user-subscriptions`, userSubscription, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    updateUserSubscription: (id, userSubscription) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/user-subscriptions/${id}`, userSubscription, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    deleteUserSubscription: (userSubscriptionId) => {
        const token = localStorage.getItem('token');
        return axios.delete(`${API_URL}/user-subscriptions/${userSubscriptionId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    createSubscriptionForUser: (userId, userSubscription) => {
        const token = localStorage.getItem('token');
        return axios.post(`${API_URL}/user-subscriptions/create-for-user?userId=${userId}`, userSubscription, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },

    getCategories: async () => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if (token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/categories`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        }
    },

    createCategory: (category) => {
        const token = localStorage.getItem('token');
        return axios.post(`${API_URL}/categories`, category, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
    },

    updateCategory: (id, category) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/categories/${id}`, category, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
    },

    deleteCategory: (id) => {
        const token = localStorage.getItem('token');
        return axios.delete(`${API_URL}/categories/${id}`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
    },

    getBookCategories: async () => {
        let token = localStorage.getItem('token');
        let refreshToken = localStorage.getItem('refresh_token');

        if (token && refreshToken) {
            if (isTokenExpired(token)) {
                const response = await ApiService.refreshToken(refreshToken);
                token = response.data.token;
                localStorage.setItem('token', token);
            }
            return axios.get(`${API_URL}/book-categories`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        }
    },

    createBookCategory: (bookCategory) => {
        const token = localStorage.getItem('token');
        return axios.post(`${API_URL}/book-categories`, bookCategory, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
    },

    updateBookCategory: (id, bookCategory) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/book-categories/${id}`, bookCategory, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
    },

    deleteBookCategory: (id) => {
        const token = localStorage.getItem('token');
        return axios.delete(`${API_URL}/book-categories/${id}`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
    },

    // Dashboard methods
    getLibrariesDashboard: async () => {
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
    getCartByUserId: async (userId) => {
        const token = localStorage.getItem('token');
        return await axios.get(`http://localhost:8080/api/cart/${userId}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
    },
    addItemToCart: (cartId, bookId) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/cart/${cartId}/add-item/${bookId}`, {}, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
    removeItemFromCart: (cartId, cartItemId) => {
        const token = localStorage.getItem('token');
        return axios.put(`${API_URL}/cart/${cartId}/remove-item/${cartItemId}`, {}, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },

    // Stripe Integration
    createPaymentIntent: (paymentInfo) => {
        const token = localStorage.getItem('token');
        return axios.post(`${API_URL}/payment/secure/payment-intent`, paymentInfo, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    },
};

const isTokenExpired = (token) => {
    const decoded = jwtDecode(token);
    const currentTime = Date.now() / 1000;
    return decoded.exp < currentTime;
};

export default ApiService;
