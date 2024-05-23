import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext.jsx';
import { Link } from 'react-router-dom';
import loginpageimage from '../../assets/login-page-image.jpg';

const LoginForm = () => {
    const [credentials, setCredentials] = useState({ username: '', password: '' });
    const [errors, setErrors] = useState({});
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
        if (!validate()) {
            return;
        }
        try {
            await login(credentials);
            navigate('/admin/dashboard');
        } catch (error) {
            alert('Login failed!');
        }
    };

    return (
        <section className="vh-100">
            <div className="container-fluid">
                <div className="row">
                    <div className="col-12 col-lg-5 col-sm-5 text-black">
                        <div className="d-flex align-items-center h-custom-2 px-4 ms-xl-4 mt-auto pt-auto pt-xl-0 mt-xl-n5">
                            <form style={{ width: '23rem' }} noValidate onSubmit={handleSubmit} id="userloginform">
                                <h3 className="fw-normal mb-3 pb-3" style={{ letterSpacing: '1px' }}>Log In</h3>
                                <div className="form-outline mb-4">
                                    <input
                                        type="text"
                                        id="username"
                                        name="username"
                                        className={`form-control form-control-lg ${errors.username ? 'is-invalid' : ''}`}
                                        required
                                        value={credentials.username}
                                        onChange={handleChange}
                                    />
                                    <label className="form-label" htmlFor="username">Username</label>
                                    {errors.username && <div className="invalid-feedback">{errors.username}</div>}
                                </div>
                                <div className="form-outline mb-4">
                                    <input
                                        type="password"
                                        id="password"
                                        name="password"
                                        className={`form-control form-control-lg ${errors.password ? 'is-invalid' : ''}`}
                                        required
                                        value={credentials.password}
                                        onChange={handleChange}
                                    />
                                    <label className="form-label" htmlFor="password">Password</label>
                                    {errors.password && <div className="invalid-feedback">{errors.password}</div>}
                                </div>
                                <div className="pt-1 mb-4">
                                    <button className="btn btn-primary btn-lg btn-login btn-block" type="submit">Login</button>
                                </div>
                                <p>Don't have an account? <Link to="/register" className="link-primary">Register here</Link></p>
                            </form>
                        </div>
                    </div>
                    <div className="col-12 col-lg-7 col-sm-7 px-0 d-none d-sm-block">
                        <img src={loginpageimage} alt="Login image" className="w-100 vh-100" style={{ objectFit: 'cover', objectPosition: 'left' }} />
                    </div>
                </div>
            </div>
        </section>
    );
};

export default LoginForm;
