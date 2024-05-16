import React, { useState, useContext } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import ApiService from '../../api/ApiService';
import { AuthContext } from '../../context/AuthContext';

const RegisterForm = () => {
    const [user, setUser] = useState({ username: '', email: '', password: '', confirmPassword: '' });
    const [errors, setErrors] = useState({});
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
        if (!validate()) {
            return;
        }
        try {
            await ApiService.register(user);
            await login({ username: user.username, password: user.password });
            navigate('/users');
        } catch (error) {
            alert('Registration or login failed!');
        }
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-lg-10 col-xl-9 mx-auto">
                    <div className="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden">
                        <div className="card-img-left d-none d-md-flex">
                            {/* Background image for card set in CSS! */}
                        </div>
                        <div className="card-body p-4 p-sm-5">
                            <h5 className="card-title text-center mb-5 fw-light fs-5">Register</h5>
                            <form noValidate onSubmit={handleSubmit}>
                                <div className="form-floating mb-3">
                                    <input
                                        type="text"
                                        className={`form-control ${errors.username ? 'is-invalid' : ''}`}
                                        id="floatingInputUsername"
                                        name="username"
                                        placeholder="myusername"
                                        required
                                        autoFocus
                                        value={user.username}
                                        onChange={handleChange}
                                    />
                                    <label htmlFor="floatingInputUsername">Username</label>
                                    {errors.username && <div className="invalid-feedback">{errors.username}</div>}
                                </div>

                                <div className="form-floating mb-3">
                                    <input
                                        type="email"
                                        className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                        id="floatingInputEmail"
                                        name="email"
                                        placeholder="name@example.com"
                                        value={user.email}
                                        onChange={handleChange}
                                    />
                                    <label htmlFor="floatingInputEmail">Email address</label>
                                    {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                                </div>

                                <hr />

                                <div className="form-floating mb-3">
                                    <input
                                        type="password"
                                        className={`form-control ${errors.password ? 'is-invalid' : ''}`}
                                        id="floatingPassword"
                                        name="password"
                                        placeholder="Password"
                                        value={user.password}
                                        onChange={handleChange}
                                    />
                                    <label htmlFor="floatingPassword">Password</label>
                                    {errors.password && <div className="invalid-feedback">{errors.password}</div>}
                                </div>

                                <div className="form-floating mb-3">
                                    <input
                                        type="password"
                                        className={`form-control ${errors.confirmPassword ? 'is-invalid' : ''}`}
                                        id="floatingPasswordConfirm"
                                        name="confirmPassword"
                                        placeholder="Confirm Password"
                                        value={user.confirmPassword}
                                        onChange={handleChange}
                                    />
                                    <label htmlFor="floatingPasswordConfirm">Confirm Password</label>
                                    {errors.confirmPassword && <div className="invalid-feedback">{errors.confirmPassword}</div>}
                                </div>

                                <div className="d-grid mb-2">
                                    <button className="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit">Register</button>
                                </div>
                                <Link className="d-block text-center mt-2 small" to="/login">Have an account? Sign In</Link>
                                <hr className="my-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegisterForm;
