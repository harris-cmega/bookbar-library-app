import React, { useState, useEffect, useRef } from 'react';
import { Link } from 'react-router-dom';
import ApiService from '../../api/ApiService';
import './Search.css';

const Search = () => {
    const [query, setQuery] = useState('');
    const [results, setResults] = useState([]);
    const dropdownRef = useRef(null);

    useEffect(() => {
        if (query.length >= 3) {
            const fetchResults = async () => {
                try {
                    const response = await ApiService.searchPublicBooks(query);
                    setResults(response);
                } catch (error) {
                    console.error('Error fetching search results:', error);
                }
            };
            fetchResults();
        } else {
            setResults([]);
        }
    }, [query]);

    const handleClear = () => {
        setQuery('');
        setResults([]);
    };

    return (
        <div className="search-bar dropdown" ref={dropdownRef}>
            <div className="input-group">
                <span className="input-group-text"><i className="bi bi-search"></i></span>
                <input
                    type="text"
                    className="form-control"
                    placeholder="Search"
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                />
                <button className="btn btn-outline-secondary close-icon" onClick={handleClear}>
                    <i className="bi bi-x"></i>
                </button>
            </div>
            {(results.length > 0 || query.length >= 3) && (
                <ul className="dropdown-menu show">
                    {results.length > 0 ? (
                        results.slice(0, 3).map((result) => (
                            <li key={result.id} className="dropdown-item">
                                <Link to={`/books/${result.id}`} className="text-decoration-none text-dark">
                                    {result.title} by {result.author_name}
                                </Link>
                            </li>
                        ))
                    ) : (
                        <li className="dropdown-item disabled">No results found</li>
                    )}
                </ul>
            )}
        </div>
    );
};

export default Search;
