import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import ApiService from '../../api/ApiService';
import Layout from '../../components/layouts/Layout';
import { StarIcon, HeartIcon } from '@heroicons/react/24/outline';
import { HeartIcon as HeartSolidIcon } from '@heroicons/react/24/solid';

const BookDetails = () => {
    const { id } = useParams();
    const [book, setBook] = useState(null);
    const [rating, setRating] = useState(4); // Example rating
    const [isInWishlist, setIsInWishlist] = useState(false);

    useEffect(() => {
        const fetchBook = async () => {
            try {
                const response = await ApiService.getPublicBookById(id);
                setBook(response.data);
            } catch (error) {
                console.error('Error fetching book details:', error);
            }
        };
        fetchBook();
    }, [id]);

    const toggleWishlist = () => {
        setIsInWishlist(!isInWishlist);
    };

    if (!book) {
        return <div>Loading...</div>;
    }

    const imageUrl = book.image ? `http://localhost:8080/${book.image}` : `http://localhost:8080/public/ph/placeholder.png`;


    return (
        <Layout>
            <div className="container mt-5 mb-5">
                <div className="row">
                    <div className="col-md-4">
                        <img
                            src={imageUrl}
                            alt={book.title}
                            className="img-fluid rounded"
                            style={{width: '500px', height: '600px', objectFit: 'cover'}}
                        />
                    </div>
                    <div className="col-md-8">
                        <h1>{book.title}</h1>
                        <div className="d-flex align-items-center">
                            <p className="me-2"><strong>Author:</strong> {book.author_name}</p>

                        </div>
                        <p><strong>Publisher:</strong> {book.publisher_name}</p>
                        <p><strong>Language:</strong> {book.language}</p>
                        <p><strong>Publication Date:</strong> {book.publication_date}</p>
                        <p><strong>Description:</strong> {book.description}</p>
                        <p><strong>Pages:</strong> {book.page_number}</p>
                        <p><strong>Price:</strong> ${book.price}</p>
                        <div className="d-flex align-items-center">
                            <p className="me-2"><strong>Rating:</strong></p>
                            {[...Array(5)].map((star, index) => (
                                <StarIcon
                                    key={index}
                                    className={`icon ${index < rating ? 'text-warning' : 'text-muted'}`}
                                    style={{width: '1.5rem', height: '1.5rem'}}
                                />
                            ))}
                        </div>
                        <button className="btn btn-primary mt-3">Order</button>
                        <button
                            className="btn btn-outline-secondary mt-3 ms-3"
                            onClick={toggleWishlist}
                        >
                            {isInWishlist
                                ? <HeartSolidIcon className="icon" style={{width: '1.4rem', height: '1.4rem'}}/>
                                : <HeartIcon className="icon" style={{width: '1.4rem', height: '1.4rem'}}/>
                            }
                        </button>
                    </div>
                </div>
            </div>
        </Layout>
    );
};

export default BookDetails;
