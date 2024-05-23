import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import PrivateRoute from './components/PrivateRoute';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import Users from './pages/Users';
import AdminLayout from './components/layouts/AdminLayout';
import AuthorsPage from './pages/admin/AuthorsPage';
import BooksPage from './pages/admin/BooksPage';
import LibrariesPage from './pages/admin/LibrariesPage';
import PublishersPage from './pages/admin/PublishersPage';
import UsersPage from './pages/admin/UsersPage';
import { Roles } from './utils/Roles';

const App = () => {
    return (
        <Router>
            <AuthProvider>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/users" element={<PrivateRoute role={Roles.USER}><Users /></PrivateRoute>} />
                    <Route path="/admin/*" element={<PrivateRoute role={Roles.ADMIN}><AdminLayout /></PrivateRoute>}>
                        <Route path="authors" element={<AuthorsPage />} />
                        <Route path="books" element={<BooksPage />} />
                        <Route path="libraries" element={<LibrariesPage />} />
                        <Route path="publishers" element={<PublishersPage />} />
                        <Route path="users" element={<UsersPage />} />
                    </Route>
                </Routes>
            </AuthProvider>
        </Router>
    );
};

export default App;
