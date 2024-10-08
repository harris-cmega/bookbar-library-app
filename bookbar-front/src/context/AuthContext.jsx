import React, { createContext, useState, useEffect } from 'react';
import ApiService from '../api/ApiService';
import { jwtDecode } from 'jwt-decode';
import {Roles} from "../utils/Roles.js";
import { useNavigate } from 'react-router-dom';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();


    const login = async (credentials) => {
        try {
            const response = await ApiService.login(credentials);
            const { token, refresh_token } = response.data;
            localStorage.setItem('token', token);
            localStorage.setItem('refresh_token', refresh_token);
            const decodedUser = jwtDecode(token);
            setUser(decodedUser);
        } catch (error) {
            console.error('Login failed:', error);
            throw error;
        }
    };

    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('refresh_token');
        setUser(null)
    };

    useEffect(() => {
        const token = localStorage.getItem('token');
        const refreshToken = localStorage.getItem('refresh_token');
        if (token && refreshToken) {
            if (!isTokenExpired(token)) {
                const decodedUser = jwtDecode(token);
                setUser(decodedUser);
            } else {
                localStorage.removeItem('token');
                localStorage.removeItem('refresh_token');
            }
        }
        setLoading(false);
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

const isTokenExpired = (token) => {
    const decoded = jwtDecode(token);
    const currentTime = Date.now() / 1000;
    return decoded.exp < currentTime;
};
