import { useContext, useEffect } from 'react';
import { AuthContext } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';
import {Roles} from "../utils/Roles.js";

const useAdminAuth = () => {
    const { user } = useContext(AuthContext);
    const navigate = useNavigate();

    useEffect(() => {
        if (!user || user.role !== Roles.ADMIN) {
            navigate('/login');
        }
    }, [user, navigate]);
};

export default useAdminAuth;
