import React from 'react';
import ApiService from "../../api/ApiService";
import {jwtDecode} from "jwt-decode";

const CartItemCard = ({ item, onRemove }) => {
    const { book, id } = item;
    const imageUrl = book.image ? `http://localhost:8080/${book.image}` : `http://localhost:8080/public/ph/placeholder.png`;

    const handleRemove = async () => {
        try {
            const token = localStorage.getItem('token');
            const decodedToken = jwtDecode(token);
            const userId = decodedToken.userId;
            await ApiService.removeItemFromCart(userId, id);
            onRemove(id);
            alert('Item removed from cart!');
        } catch (error) {
            console.error('Error removing item from cart:', error);
            alert('Failed to remove item from cart');
        }
    };

    return (
        <div className="card mb-4">
            <img
                src={imageUrl}
                className="card-img-top"
                alt={book.title}
            />
            <div className="card-body">
                <h5 className="card-title">{book.title}</h5>
                <p className="card-text">Author: {book.author_name}</p>
                <p className="card-text">Price: ${book.price}</p>
                <button
                    className="btn btn-danger"
                    onClick={handleRemove}
                >
                    Remove
                </button>
            </div>
        </div>
    );
};

export default CartItemCard;
