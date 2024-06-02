import React, { useState, useEffect } from 'react';
import ApiService from '../../api/ApiService';
import { Table, Button, Form } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal';

const CategoriesPage = () => {
    const [categories, setCategories] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newCategory, setNewCategory] = useState({ name: '' });
    const [editCategory, setEditCategory] = useState(null);
    const [deleteCategory, setDeleteCategory] = useState(null);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchCategories();
    }, []);

    const fetchCategories = async () => {
        try {
            const response = await ApiService.getCategories();
            setCategories(response.data);
        } catch (error) {
            console.error('Error fetching categories:', error);
            setError('Error fetching categories.');
        }
    };

    const handleAddCategory = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.createCategory(newCategory);
            setCategories(prevCategories => [...prevCategories, response.data]);
            handleCloseModal();
        } catch (error) {
            console.error('Failed to add category:', error);
            setError('Failed to add category.');
        }
    };

    const handleEditClick = (category) => {
        setEditCategory({ id: category.id, name: category.name });
        setShowModal(true);
    };

    const handleUpdateCategory = async (event) => {
        event.preventDefault();
        try {
            const response = await ApiService.updateCategory(editCategory.id, editCategory);
            setCategories(prevCategories => prevCategories.map(category => category.id === editCategory.id ? response.data : category));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to update category:', error);
            setError('Failed to update category.');
        }
    };

    const handleDeleteCategory = async () => {
        try {
            await ApiService.deleteCategory(deleteCategory.id);
            setCategories(prevCategories => prevCategories.filter(category => category.id !== deleteCategory.id));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to delete category:', error);
            setError('Failed to delete category.');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (deleteCategory) {
            await handleDeleteCategory();
        } else if (editCategory) {
            await handleUpdateCategory(event);
        } else {
            await handleAddCategory(event);
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setEditCategory(null);
        setDeleteCategory(null);
        setNewCategory({ name: '' });
    };

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-2">
                <h3>Manage Categories</h3>
                <Button variant="btn btn-primary my-4" onClick={() => setShowModal(true)}>Add Category</Button>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
            <ReusableModal
                show={showModal}
                handleClose={handleCloseModal}
                handleSubmit={handleSubmit}
                title={deleteCategory ? "Confirm Delete" : (editCategory ? "Edit Category" : "Add New Category")}
            >
                {deleteCategory ? (
                    <p>Are you sure you want to delete {deleteCategory.name}?</p>
                ) : (
                    <Form.Control
                        type="text"
                        placeholder="Enter category name"
                        className="mb-2"
                        value={editCategory ? editCategory.name : newCategory.name}
                        onChange={e => editCategory ? setEditCategory({
                            ...editCategory,
                            name: e.target.value
                        }) : setNewCategory({...newCategory, name: e.target.value})}
                    />
                )}
            </ReusableModal>
            <Table hover className="text-center mt-2">
                <thead className="bg-primary text-white">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {categories.map(category => (
                    <tr key={category.id}>
                        <td>{category.id}</td>
                        <td>{category.name}</td>
                        <td>
                            <Button variant="outline-secondary btn-sm" className="me-2" onClick={() => handleEditClick(category)}>Edit</Button>
                            <Button variant="danger btn-sm" onClick={() => {
                                setDeleteCategory(category);
                                setShowModal(true);
                            }}>Delete</Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
};

export default CategoriesPage;
