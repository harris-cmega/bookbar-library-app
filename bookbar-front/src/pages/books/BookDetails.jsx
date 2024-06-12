import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import ApiService from '../../api/ApiService';
import Layout from '../../components/layouts/Layout';
import { StarIcon, HeartIcon } from '@heroicons/react/24/outline';
import { HeartIcon as HeartSolidIcon } from '@heroicons/react/24/solid';
import {
    ReactReader,
    ReactReaderStyle
} from 'react-reader';

const BookDetails = () => {
    const { id } = useParams();
    const [book, setBook] = useState(null);
    const [rating, setRating] = useState(4); // Example rating
    const [isInWishlist, setIsInWishlist] = useState(false);
    const [bookFiles, setBookFiles] = useState([]);
    const [epubUrls, setEpubUrls] = useState([]);
    const [selectedFile, setSelectedFile] = useState(null);
    const [location, setLocation] = useState(null);

    useEffect(() => {
        const fetchBookDetails = async () => {
            try {
                const bookResponse = await ApiService.getPublicBookById(id);
                setBook(bookResponse.data);
            } catch (error) {
                console.error('Error fetching book details:', error);
            }
        };

        const fetchBookFiles = async () => {
            try {
                const bookFilesResponse = await ApiService.getBookFiles();
                const allBookFiles = bookFilesResponse.data || [];
                
                // Filter book files based on bookId
                const filteredBookFiles = allBookFiles.filter(file => file.bookId === parseInt(id));
                setBookFiles(filteredBookFiles);
                
                // Set epubUrls
                const epubUrls = filteredBookFiles.map(file => file.epubfile);
                setEpubUrls(epubUrls);
            } catch (error) {
                console.error('Error fetching book files:', error);
            }
        };
        
        

        fetchBookFiles();
        fetchBookDetails();
    }, [id]);

    const toggleWishlist = () => {
        setIsInWishlist(!isInWishlist);
    };

    const onLocationChanged = (epubcifi) => {
        setLocation(epubcifi);
    };

    const handleFileSelect = (file) => {
        setSelectedFile(file);
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
                        <button className="btn btn-primary mt-3">Order</button>
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
            {bookFiles.length > 0 && (
                <div className="row my-5 mx-5">
                    <div className="col-12">
                        <h2>Select File to Read</h2>
                        <div className="list-group">
                            {bookFiles.map(file => (
                                <button
                                    key={file.id}
                                    type="button"
                                    className={`list-group-item list-group-item-action ${selectedFile === file ? 'active' : ''}`}
                                    onClick={() => handleFileSelect(file)}
                                >
                                    {file.filename}
                                </button>
                            ))}
                        </div>
                    </div>
                </div>
            )}
            {selectedFile && (
                <div className="row my-3 mx-3">
                    <div className="col-12">
                        <h2>Read Book</h2>
                        <div className="vh-100">
                            <ReactReader
                                url={`http://localhost:8080/${selectedFile.epubFile}`} // Assuming the URL is correct
                                title={selectedFile.filename}
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
