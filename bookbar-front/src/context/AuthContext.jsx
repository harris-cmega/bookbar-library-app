import React, { createContext, useState, useEffect } from 'react';
import ApiService from '../api/ApiService';
import {jwtDecode} from 'jwt-decode';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    const login = async (credentials) => {
        try {
            const response = await ApiService.login(credentials);
            const token = response.data.token;
            localStorage.setItem('token', token);
            const decodedUser = jwtDecode(token);
            setUser(decodedUser);
        } catch (error) {
            console.error('Login failed:', error);
            throw error;
        }
    };

    const logout = () => {
        localStorage.removeItem('token');
        setUser(null);
    };

    const isTokenExpired = (token) => {
        const decoded = jwtDecode(token);
        const currentTime = Date.now() / 1000;
        return decoded.exp < currentTime;
    };

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            console.log('Token found:', token);
            if (!isTokenExpired(token)) {
                try {
                    const decodedUser = jwtDecode(token);
                    console.log('Decoded user:', decodedUser);
                    setUser(decodedUser);
                } catch (error) {
                    console.error('Invalid token:', error);
                    localStorage.removeItem('token');
                }
            } else {
                console.log('Token expired');
                localStorage.removeItem('token');
            }
        } else {
            console.log('No token found');
        }
        setLoading(false); // Set loading to false after checking the token
    }, []);

    if (loading) {
        return <div>Loading...</div>; // You can replace this with a better loading indicator if needed
    }

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
