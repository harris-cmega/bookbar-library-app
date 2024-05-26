import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext.jsx';
import { Link } from 'react-router-dom';
import LoginPageImg from '../../assets/login-page-image.jpg';

const LoginForm = () => {
    const [credentials, setCredentials] = useState({ username: '', password: '' });
    const [errors, setErrors] = useState({});
    const [serverError, setServerError] = useState('');
    const { login } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCredentials({ ...credentials, [name]: value });
    };

    const validate = () => {
        let errors = {};
        if (!credentials.username) {
            errors.username = 'Username is required';
        }
        if (!credentials.password) {
            errors.password = 'Password is required';
        }
        setErrors(errors);
        return Object.keys(errors).length === 0;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setServerError(''); // Reset server error on new submit
        if (!validate()) {
            return;
        }
        try {
            await login(credentials);
            navigate('/admin/dashboard');
        } catch (error) {
            setServerError('Login failed! Please check your username and password.');
        }
    };

    return (
        <section className="vh-100">
            <div className="container-fluid">
                <div className="row">
                    <div className="col-12 col-lg-5 col-md-6 offset-lg-1 offset-md-2 text-black">
                        <div className="d-flex align-items-center h-custom-2 px-4 ms-xl-4 mt-auto pt-auto pt-xl-0 mt-xl-n5">
                            <form style={{ width: '20rem' }} noValidate onSubmit={handleSubmit} id="userloginform">
                                <h3 className="fw-bold mb-5 pb-3" style={{ letterSpacing: '1px' }}>Log In</h3>
                                {serverError && <div className="alert alert-danger">{serverError}</div>}
                                <div className="form-outline mb-4">
                                    <label className="form-label" htmlFor="username">Username</label>
                                    <input
                                        type="text"
                                        id="username"
                                        name="username"
                                        className={`form-control form-control-lg ${errors.username ? 'is-invalid' : ''}`}
                                        required
                                        value={credentials.username}
                                        onChange={handleChange}
                                    />
                                    {errors.username && <div className="invalid-feedback">{errors.username}</div>}
                                </div>
                                <div className="form-outline mb-4">
                                    <label className="form-label" htmlFor="password">Password</label>
                                    <input
                                        type="password"
                                        id="password"
                                        name="password"
                                        className={`form-control form-control-lg ${errors.password ? 'is-invalid' : ''}`}
                                        required
                                        value={credentials.password}
                                        onChange={handleChange}
                                    />
                                    {errors.password && <div className="invalid-feedback">{errors.password}</div>}
                                </div>
                                <div className="pt-1 mb-4">
                                    <button className="btn btn-primary btn-lg btn-login btn-block" type="submit">Login</button>
                                </div>
                                <p>Don't have an account? <Link to="/register" className="link-primary">Register here</Link></p>
                            </form>
                        </div>
                    </div>
                    <div className="col-12 col-lg-6 col-md-4 d-none d-sm-block px-0">
                        <img src={LoginPageImg} alt="Login image" className="w-100 vh-100" style={{ objectFit: 'cover', objectPosition: 'left' }} />
                    </div>
                </div>
            </div>
        </section>
    );
};

export default LoginForm;
