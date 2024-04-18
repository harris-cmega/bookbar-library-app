import React from 'react';
import {Link} from "react-router-dom";

const RegisterForm = () => {
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
                            <form>

                                <div className="form-floating mb-3">
                                    <input type="text" className="form-control" id="floatingInputUsername" placeholder="myusername" required autoFocus />
                                    <label htmlFor="floatingInputUsername">Username</label>
                                </div>

                                <div className="form-floating mb-3">
                                    <input type="email" className="form-control" id="floatingInputEmail" placeholder="name@example.com" />
                                    <label htmlFor="floatingInputEmail">Email address</label>
                                </div>

                                <hr />

                                <div className="form-floating mb-3">
                                    <input type="password" className="form-control" id="floatingPassword" placeholder="Password" />
                                    <label htmlFor="floatingPassword">Password</label>
                                </div>

                                <div className="form-floating mb-3">
                                    <input type="password" className="form-control" id="floatingPasswordConfirm" placeholder="Confirm Password" />
                                    <label htmlFor="floatingPasswordConfirm">Confirm Password</label>
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
