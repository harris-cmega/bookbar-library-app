import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

const Navbar = () => {
  const { user, logout } = useContext(AuthContext);

  return (
      <nav className="navbar navbar-expand-lg pt-3 pb-3">
        <div className="container-fluid">
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                  data-bs-target="#navbarExample" aria-controls="navbarExample" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <a className="navbar-brand" href="#">BookBar</a>
          <div className="collapse navbar-collapse" id="navbarExample">
            <ul className="navbar-nav me-auto mb-0">
              <li className="nav-item ps-3">
                <a className="nav-link active" aria-current="page" href="/">Home</a>
              </li>
              <li className="nav-item ps-3">
                <a className="nav-link" aria-current="page" href="#">Books</a>
              </li>
              <li className="nav-item ps-3">
                <a className="nav-link" aria-current="page" href="#">Accessories</a>
              </li>
              <li className="nav-item ps-3">
                <Link className="nav-link" to="/subscription">Subscription</Link>
              </li>
              <li className="nav-item ps-3">
                <a className="nav-link" aria-current="page" href="#">eBooks</a>
              </li>
              <li className="nav-item ps-3">
                <a className="nav-link" aria-current="page" href="#">Locations</a>
              </li>
              <li className="nav-item ps-3 dropdown">
                <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Categories</a>
                <ul className="dropdown-menu">
                  <li><a className="dropdown-item" href="#">Action</a></li>
                  <li><a className="dropdown-item" href="#">Another action</a></li>
                  <li><hr className="dropdown-divider" /></li>
                  <li><a className="dropdown-item" href="#">Something else here</a></li>
                </ul>
              </li>
            </ul>
            <div className="d-flex align-items-center flex-column flex-lg-row">
              <form className="me-4 mb-2 mb-lg-0">
                <input type="text" className="form-control form-control-sm" placeholder="Search by Title, Author, Keyword or ISBN" />
              </form>
              {user ? (
                  <>
                    <button className="btn btn-danger me-2" onClick={logout}>Logout</button>
                    <span className="navbar-text me-2">Welcome, {user.sub}</span>
                  </>
              ) : (
                  <>
                    <Link className="btn btn-primary me-2" to="/login">Login</Link>
                    <Link className="btn btn-secondary" to="/register">Sign up</Link>
                  </>
              )}
            </div>
          </div>
        </div>
      </nav>
  );
}

export default Navbar;
