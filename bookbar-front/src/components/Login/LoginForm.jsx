import React from 'react';
import { Link } from 'react-router-dom';
import loginpageimage from '../../assets/login-page-image.jpg';

const LoginForm = () => {
    return (
        <section class="vh-100">
            <div className="container-fluid">
                <div className="row">
                    <div className="col-12 col-lg-5 col-sm-5 text-black">
                        <div className="d-flex align-items-center h-custom-2 px-4 ms-xl-4 mt-auto pt-auto pt-xl-0 mt-xl-n5">
                            <form style={{ width: '23rem' }} noValidate id="userloginform">

                                <h3 className="fw-normal mb-3 pb-3" style={{ letterSpacing: '1px' }}>Log In</h3>

                                <div className="form-outline mb-4">
                                    <input type="email" id="" className="form-control form-control-lg" required/>
                                    <label className="form-label" htmlFor="email">Email</label>
                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="" className="form-control form-control-lg" />
                                    <label className="form-label" htmlFor="password">Password</label>
                                </div>

                                <div className="pt-1 mb-4">
                                    <button className="btn btn-primary btn-lg btn-login btn-block" type="submit">Login</button>
                                </div>
                                <p>Don't have an account? <Link to="/signup" className="link-primary">Register here</Link></p>

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
