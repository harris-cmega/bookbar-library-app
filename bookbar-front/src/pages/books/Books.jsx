import React, { useState, useEffect } from 'react';
import ApiService from '../../api/ApiService';
import { Link } from 'react-router-dom';
import Layout from '../../components/layouts/Layout';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Books.css';

const Books = () => {
    const [books, setBooks] = useState([]);
    const [categories, setCategories] = useState([]);
    const [authors, setAuthors] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState(null);
    const [selectedAuthor, setSelectedAuthor] = useState(null);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        fetchBooks();
        fetchCategories();
        fetchAuthors();
    }, []);

    useEffect(() => {
        fetchBooks();
    }, [selectedCategory, selectedAuthor, page]);

    const fetchBooks = async () => {
        try {
            const response = await ApiService.getPublicBooks(page);
            setBooks(response.data.content);
            setTotalPages(response.data.totalPages);
        } catch (error) {
            console.error('Error fetching books:', error);
        }
    };

    const fetchCategories = async () => {
        try {
            const response = await ApiService.getPublicCategories();
            setCategories(response.data);
        } catch (error) {
            console.error('Error fetching categories:', error);
        }
    };

    const fetchAuthors = async () => {
        try {
            const response = await ApiService.getPublicAuthors();
            setAuthors(response.data);
        } catch (error) {
            console.error('Error fetching authors:', error);
        }
    };

    const handleCategoryClick = (category) => {
        setSelectedCategory(category);
        setPage(0); // Reset to the first page when changing category
    };

    const handleAuthorClick = (author) => {
        setSelectedAuthor(author);
        setPage(0); // Reset to the first page when changing author
    };

    const filteredBooks = books.filter(book => {
        return (
            (!selectedCategory || book.categories.some(cat => cat.id === selectedCategory.id)) &&
            (!selectedAuthor || book.author_name === selectedAuthor.name)
        );
    });

    const handlePageChange = (newPage) => {
        setPage(newPage);
    };

    return (
        <Layout>
            <div className="container mt-5 mb-5">
                <h1>Books</h1>
                <div className="row">
                    <div className="col-md-3">
                        <h4>Categories</h4>
                        <ul className="list-group">
                            <li
                                className={`list-group-item ${!selectedCategory ? 'active' : ''}`}
                                onClick={() => handleCategoryClick(null)}
                            >
                                All
                            </li>
                            {categories.map(category => (
                                <li
                                    key={category.id}
                                    className={`list-group-item ${selectedCategory && selectedCategory.id === category.id ? 'active' : ''}`}
                                    onClick={() => handleCategoryClick(category)}
                                >
                                    {category.name}
                                </li>
                            ))}
                        </ul>
                        <h4 className="mt-4">Authors</h4>
                        <ul className="list-group">
                            <li
                                className={`list-group-item ${!selectedAuthor ? 'active' : ''}`}
                                onClick={() => handleAuthorClick(null)}
                            >
                                All
                            </li>
                            {authors.map(author => (
                                <li
                                    key={author.id}
                                    className={`list-group-item ${selectedAuthor && selectedAuthor.id === author.id ? 'active' : ''}`}
                                    onClick={() => handleAuthorClick(author)}
                                >
                                    {author.name}
                                </li>
                            ))}
                        </ul>
                    </div>
                    <div className="col-md-9">
                        <div className="row">
                            {filteredBooks.map((book) => (
                                <div className="col-md-4 mb-4" key={book.id}>
                                    <div className="card mb-4 h-100">
                                        <img
                                            src={book.image ? `http://localhost:8080/${book.image}` : `http://localhost:8080/public/ph/placeholder.png`}
                                            className="card-img-top"
                                            alt={book.title}
                                            style={{ height: '250px', objectFit: 'cover' }}
                                        />
                                        <div className="card-body d-flex flex-column">
                                            <h5 className="card-title">{book.title}</h5>
                                            <p className="card-text">by {book.author_name}</p>
                                            <Link to={`/books/${book.id}`} className="btn btn-primary mt-auto">
                                                View Details
                                            </Link>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                        <div className="pagination d-flex justify-content-center mt-4">
                            {[...Array(totalPages).keys()].map((number) => (
                                <button
                                    key={number}
                                    className={`btn mx-1 ${number === page ? 'btn-primary' : 'btn-secondary'}`}
                                    onClick={() => handlePageChange(number)}
                                >
                                    {number + 1}
                                </button>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </Layout>
    );
};

export default Books;
