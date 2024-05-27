import React, { useState, useContext } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import ApiService from '../../api/ApiService';
import { AuthContext } from '../../context/AuthContext';
import RegisterPageImg from '../../assets/login-page-image.jpg'; // Assuming you want to use the same image

const RegisterForm = () => {
    const [user, setUser] = useState({ username: '', email: '', password: '', confirmPassword: '' });
    const [errors, setErrors] = useState({});
    const [serverError, setServerError] = useState('');
    const navigate = useNavigate();
    const { login } = useContext(AuthContext);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUser({ ...user, [name]: value });
    };

    const validate = () => {
        let errors = {};
        if (!user.username) {
            errors.username = 'Username is required';
        }
        if (!user.email) {
            errors.email = 'Email is required';
        }
        if (!user.password) {
            errors.password = 'Password is required';
        }
        if (user.password !== user.confirmPassword) {
            errors.confirmPassword = 'Passwords do not match';
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
            await ApiService.register(user);
            await login({ username: user.username, password: user.password });
            navigate('/users');
        } catch (error) {
            setServerError('Registration failed!');
        }
    };

    return (
        <section className="vh-100">
            <div className="container-fluid">
                <div className="row">
                    <div className="col-12 col-lg-6 col-md-4 d-none d-sm-block px-0">
                        <img src={RegisterPageImg} alt="Register image" className="w-100 vh-100" style={{ objectFit: 'cover', objectPosition: 'left' }} />
                    </div>
                    <div className="col-12 col-lg-5 col-md-6 offset-lg-1 offset-md-2 text-black">
                        <div className="d-flex align-items-center h-custom-2 px-4 ms-xl-4 mt-auto pt-auto pt-xl-0 mt-xl-n5">
                            <form style={{ width: '20rem' }} noValidate onSubmit={handleSubmit} id="userRegisterForm">
                                <h3 className="fw-bold mb-5 pb-3" style={{ letterSpacing: '1px' }}>Register</h3>
                                {serverError && <div className="alert alert-danger">{serverError}</div>}
                                <div className="form-outline mb-4">
                                    <label className="form-label" htmlFor="floatingInputUsername">Username</label>
                                    <input
                                        type="text"
                                        id="floatingInputUsername"
                                        name="username"
                                        className={`form-control ${errors.username ? 'is-invalid' : ''}`}
                                        placeholder="myusername"
                                        required
                                        value={user.username}
                                        onChange={handleChange}
                                    />
                                    {errors.username && <div className="invalid-feedback">{errors.username}</div>}
                                </div>

                                <div className="form-outline mb-4">
                                    <label className="form-label" htmlFor="floatingInputEmail">Email address</label>
                                    <input
                                        type="email"
                                        id="floatingInputEmail"
                                        name="email"
                                        className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                        placeholder="name@example.com"
                                        value={user.email}
                                        onChange={handleChange}
                                    />
                                    {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                                </div>

                                <div className="form-outline mb-4">
                                    <label className="form-label" htmlFor="floatingPassword">Password</label>
                                    <input
                                        type="password"
                                        id="floatingPassword"
                                        name="password"
                                        className={`form-control ${errors.password ? 'is-invalid' : ''}`}
                                        placeholder="Password"
                                        value={user.password}
                                        onChange={handleChange}
                                    />
                                    {errors.password && <div className="invalid-feedback">{errors.password}</div>}
                                </div>

                                <div className="form-outline mb-5">
                                    <label className="form-label" htmlFor="floatingPasswordConfirm">Confirm Password</label>
                                    <input
                                        type="password"
                                        id="floatingPasswordConfirm"
                                        name="confirmPassword"
                                        className={`form-control ${errors.confirmPassword ? 'is-invalid' : ''}`}
                                        placeholder="Confirm Password"
                                        value={user.confirmPassword}
                                        onChange={handleChange}
                                    />
                                    {errors.confirmPassword && <div className="invalid-feedback">{errors.confirmPassword}</div>}
                                </div>

                                <div className="mb-2">
                                    <button className="btn btn-primary btn-lg btn-login btn-block" type="submit">Register</button>
                                </div>
                                <span className="d-block mt-2 small">
                                    Already have an account? <Link to="/login">Sign In</Link>
                                </span>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default RegisterForm;
