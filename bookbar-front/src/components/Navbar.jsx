import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import Search from './search/Search.jsx'; // Import the Search component

const Navbar = () => {
  const { user, logout } = useContext(AuthContext);

  return (
      <nav className="navbar navbar-expand-lg pt-3 pb-3">
        <div className="container">
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                  data-bs-target="#navbarExample" aria-controls="navbarExample" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <a className="navbar-brand" href="/">BookBar</a>
          <div className="collapse navbar-collapse" id="navbarExample">
            <ul className="navbar-nav me-auto mb-0">
              <li className="nav-item ps-3">
              </li>
              <li className="nav-item ps-3">
                <a className="nav-link" aria-current="page" href="/books">Books</a>
              </li>
              <li className="nav-item ps-3">
                <a className="nav-link" aria-current="page" href="/accessories">Accessories</a>
              </li>
              <li className="nav-item ps-3">
                <Link className="nav-link" to="/subscription">Subscription</Link>
              </li>
              <li className="nav-item ps-3">
                <a className="nav-link" aria-current="page" href="/ebooks">eBooks</a>
              </li>
            </ul>
            <div className="d-flex align-items-center flex-column flex-lg-row">
              <Search />
              {user ? (
                  <>
                    <button className="btn btn-danger me-2" onClick={logout}>Logout</button>
                    <span className="navbar-text me-2">Welcome, {user.sub}</span>
                  </>
              ) : (
                  <>
                    <Link className="btn btn-primary me-2" to="/login">Login</Link>
                    <Link className="btn btn-secondary" to="/register">Register</Link>
                  </>
              )}
            </div>
          </div>
        </div>
      </nav>
  );
}

export default Navbar;
