import React, { useState, useEffect } from 'react';
import ApiService from '../../api/ApiService';
import { Link } from 'react-router-dom';
import Layout from '../../components/layouts/Layout';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Books.css';

const Books = () => {
    const [books, setBooks] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        const fetchBooks = async () => {
            try {
                const response = await ApiService.getPublicBooks(page);
                setBooks(response.data.content);
                setTotalPages(response.data.totalPages);
            } catch (error) {
                console.error('Error fetching books:', error);
            }
        };
        fetchBooks();
    }, [page]);

    const handlePageChange = (newPage) => {
        setPage(newPage);
    };

    return (
        <Layout>
            <div className="container mt-5 mb-5">
                <h1>Books</h1>
                <div className="row">
                    {books.map((book) => (
                        <div className="col-md-3 mb-4" key={book.id}>
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
        </Layout>
    );
};

export default Books;
