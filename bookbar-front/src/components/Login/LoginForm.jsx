import React from 'react';
import { Link } from 'react-router-dom';

const LoginForm = () => {
    return (
        <div className="container">
            <div className="row">
                <div className="col-12 col-lg-5">
                    <div className="card">
                        <div className="card-body">
                            <div className="card-title">Log In into your Account!</div>
                            <form action="" method="post" novalidate id="userloginform">
                                <div className="form-floating">
                                    <input type="email" id="email" className="form-control" placeholder="Place Email here" required />
                                    <label htmlFor="email">Email</label>
                                    <input type="password" id="password" className="form-control" placeholder="Enter Password here" required />
                                    <label htmlFor="password">Password</label>
                                </div>
                                <button type="submit" className='btn btn-primary'>Submit</button>
                            </form>
                        </div>
                    </div>
                    <div className="card">
                        <div className="card-text">
                            <Link className="btn btn-secondary" to="/signup">Create an Account</Link>
                        </div>
                    </div>
                </div>
                <div className="col-12 col-lg-7">
                    <img src="./assets/login-page-image.jpg" alt="Login Page Image" />
                </div>
            </div>
        </div>
    );
};

export default LoginForm;