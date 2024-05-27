import React from 'react';
import Navbar from '../Navbar.jsx';
import Footer from '../Footer.jsx';

const Layout = ({ children }) => {
    return (
        <div>
            <Navbar />
            <main>{children}</main>
            <Footer />
        </div>
    );
};

export default Layout;
