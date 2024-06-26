import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import PrivateRoute from './components/PrivateRoute';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import Subscription from './pages/Subscription';
import Users from './pages/Users';
import AdminLayout from './components/layouts/AdminLayout';
import UserSubscriptionsPage from './pages/admin/UserSubscriptionsPage'
import AuthorsPage from './pages/admin/AuthorsPage';
import BooksPage from './pages/admin/BooksPage';
import LibrariesPage from './pages/admin/LibrariesPage';
import PublishersPage from './pages/admin/PublishersPage';
import BookFilesPage from './pages/admin/BookFilesPage';
import UsersPage from './pages/admin/UsersPage';
import Success from './pages/Success';
import CategoriesPage from './pages/admin/CategoriesPage'; // Import CategoriesPage
import BookCategoriesPage from './pages/admin/BookCategoriesPage'; // Import BookCategoriesPage
import { Roles } from './utils/Roles';
import InternalServerError from "./pages/InternalServerError.jsx";
import NotFound from "./pages/NotFound.jsx";
import Forbidden from "./pages/Forbidden.jsx";
import ErrorBoundary from "./components/ErrorBoundary.jsx";
import BookDetails from "./pages/books/BookDetails.jsx";
import Books from "./pages/books/Books.jsx";
import CartPage from "./pages/CartPage.jsx"

const App = () => {
    return (
        <Router>
            <ErrorBoundary>
            <AuthProvider>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/books" element={<Books />} />
                    <Route path="/books/:id" element={<BookDetails />} />
                    <Route path="/cart" element={<CartPage />} />
                    <Route path="/subscription" element={<Subscription />} />
                    <Route path="/users" element={<PrivateRoute role={Roles.USER}><Users /></PrivateRoute>} />
                    <Route path="/admin/*" element={<PrivateRoute role={Roles.ADMIN}><AdminLayout /></PrivateRoute>}>
                        <Route path="authors" element={<AuthorsPage />} />
                        <Route path="books" element={<BooksPage />} />
                        <Route path="user-subscriptions" element={<UserSubscriptionsPage />} />
                        <Route path="book-files" element={<BookFilesPage />} />
                        <Route path="libraries" element={<LibrariesPage />} />
                        <Route path="publishers" element={<PublishersPage />} />
                        <Route path="users" element={<UsersPage />} />
                        <Route path="categories" element={<CategoriesPage />} />
                        <Route path="book-categories" element={<BookCategoriesPage />} />
                    </Route>
                    <Route path="/success" element={<Success />} />
                    //Error pages
                    <Route path="/500" element={<InternalServerError />} />
                    <Route path="/403" element={<Forbidden />} />
                    <Route path="*" element={<NotFound />} />
                </Routes>
            </AuthProvider>
            </ErrorBoundary>
        </Router>
    );
};

export default App;
