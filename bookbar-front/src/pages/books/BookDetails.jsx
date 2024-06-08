import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import ApiService from '../../api/ApiService';
import Layout from '../../components/layouts/Layout';
import { StarIcon, HeartIcon } from '@heroicons/react/24/outline';
import { HeartIcon as HeartSolidIcon } from '@heroicons/react/24/solid';
import {jwtDecode} from 'jwt-decode';
import {
    ReactReader,
    ReactReaderStyle
} from 'react-reader';
import epubFile from '../../assets/atomichabits.epub'; // Import the EPUB file


const BookDetails = () => {
    const { id } = useParams();
    const [book, setBook] = useState(null);
    const [rating, setRating] = useState(4); // Example rating
    const [isInWishlist, setIsInWishlist] = useState(false);
    const [epubUrl, setEpubUrl] = useState(epubFile);
    const [location, setLocation] = useState(null);

    useEffect(() => {
        const fetchBook = async () => {
            try {
                const response = await ApiService.getPublicBookById(id);
                setBook(response.data);

                // Fetch the associated EPUB file
                // const bookFilesResponse = await ApiService.getBookFileByBookId(id);
                // if (bookFilesResponse.data.length > 0) {
                //     const bookFile = bookFilesResponse.data[0];
                //     setEpubUrl(bookFile.file);
                // }
            } catch (error) {
                console.error('Error fetching book details:', error);
            }
        };
        fetchBook();
    }, [id]);

    const addToCart = async () => {
        try {
            const token = localStorage.getItem('token');
            const decodedToken = jwtDecode(token);
            const userId = decodedToken.userId;
            const userCart = await ApiService.getCartByUserId(userId);
            const cartId = userCart.data.id;

            // Check if the book is already in the cart
            const existingCartItem = userCart.data.cartItems.find(item => item.bookId === book.id);
            if (existingCartItem) {
                alert('This book is already in your cart!');
                return;
            }

            // If the book is not in the cart, proceed to add it
            await ApiService.addItemToCart(cartId, book.id);
            alert('Book added to cart!');
        } catch (error) {
            console.error('Error adding book to cart:', error);
            alert('Failed to add book to cart');
        }
    };


    const toggleWishlist = () => {
        setIsInWishlist(!isInWishlist);
    };

    const onLocationChanged = (epubcifi) => {
        setLocation(epubcifi);
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
                            style={{ width: '500px', height: '600px', objectFit: 'cover' }}
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
                        <p><strong>Categories:</strong> {book.categories.map(category => category.name).join(', ')}</p> {/* Add this line */}
                        <div className="d-flex align-items-center">
                            <p className="me-2"><strong>Rating:</strong></p>
                            {[...Array(5)].map((star, index) => (
                                <StarIcon
                                    key={index}
                                    className={`icon ${index < rating ? 'text-warning' : 'text-muted'}`}
                                    style={{ width: '1.5rem', height: '1.5rem' }}
                                />
                            ))}
                        </div>
                        <button className="btn btn-primary mt-3" onClick={addToCart}>Add to Cart</button>
                        <button
                            className="btn btn-outline-secondary mt-3 ms-3"
                            onClick={toggleWishlist}
                        >
                            {isInWishlist
                                ? <HeartSolidIcon className="icon" style={{ width: '1.4rem', height: '1.4rem' }} />
                                : <HeartIcon className="icon" style={{ width: '1.4rem', height: '1.4rem' }} />
                            }
                        </button>
                    </div>
                </div>
            </div>
            {epubUrl && (
                <div className="row mt-5">
                    <div className="col-12">
                        <h2>Read Book</h2>
                        <div className="vh-100">
                            <ReactReader
                                url={epubUrl}
                                title={book.title}
                                location={location}
                                locationChanged={onLocationChanged}
                                readerStyle={{
                                    ...ReactReaderStyle,
                                    container: {
                                        ...ReactReaderStyle.container,
                                        backgroundColor: '#fff',
                                        color: '#000',
                                    },
                                    reader: {
                                        ...ReactReaderStyle.reader,
                                        backgroundColor: '#fff',
                                    }
                                }}
                            />
                        </div>
                    </div>
                </div>
            )}
        </Layout>
    );
}

export default BookDetails;
