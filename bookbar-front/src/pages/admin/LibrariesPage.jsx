import React, { useEffect, useState } from 'react';
import ApiService from '../../api/ApiService';
import { Button, Form, Table } from 'react-bootstrap';
import ReusableModal from '../../components/ReusableModal.jsx';
import { Country, State, City } from 'country-state-city';

const LibrariesPage = () => {
    const [libraries, setLibraries] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newLibrary, setNewLibrary] = useState({
        name: '',
        address: '',
        city: '',
        state: '', // This will hold the country code
        zip_code: '',
        phone: '',
        email: '',
        opening_hours: ''
    });
    const [editLibrary, setEditLibrary] = useState(null);
    const [deleteLibrary, setDeleteLibrary] = useState(null);
    const [error, setError] = useState('');
    const [states, setStates] = useState([]);
    const [cities, setCities] = useState([]);
    const [selectedCountry, setSelectedCountry] = useState('');

    useEffect(() => {
        fetchLibraries();
    }, []);

    const fetchLibraries = async () => {
        try {
            const response = await ApiService.getLibraries();
            setLibraries(response.data);
        } catch (error) {
            console.error('Error fetching libraries:', error);
            setError('Error fetching libraries.');
        }
    };

    const handleCountryChange = (e) => {
        const countryCode = e.target.value;
        setSelectedCountry(countryCode);
        const statesList = State.getStatesOfCountry(countryCode);
        setStates(statesList);
        setNewLibrary({ ...newLibrary, state: countryCode, city: '' }); // Set country as state
        setCities([]);
    };

    const handleStateChange = (e) => {
        const stateCode = e.target.value;
        const citiesList = City.getCitiesOfState(selectedCountry, stateCode);
        setCities(citiesList);
        setNewLibrary({ ...newLibrary, state: selectedCountry, city: '' }); // Ensure country is still in state
    };

    const handleCityChange = (e) => {
        setNewLibrary({ ...newLibrary, city: e.target.value });
    };

    const handleAddLibrary = async (event) => {
        event.preventDefault();
        const libraryToSend = {
            ...newLibrary,
            state: newLibrary.state, // Send the country code as state
            city: newLibrary.city // Only send city as selected
        };
        try {
            const response = await ApiService.createLibrary(libraryToSend);
            setLibraries(prevLibraries => [...prevLibraries, response.data]);
            handleCloseModal();
        } catch (error) {
            console.error('Failed to add library:', error.response ? error.response.data : error.message);
            setError('Failed to add library.');
        }
    };

    const handleEditClick = (library) => {
        setEditLibrary(library);
        setSelectedCountry(library.state); // Set the selected country for editing
        const statesList = State.getStatesOfCountry(library.state);
        setStates(statesList);
        const citiesList = City.getCitiesOfState(library.state, library.city);
        setCities(citiesList);
        setNewLibrary({
            ...library,
            state: library.state, // Set the country as state
            city: library.city
        });
        setShowModal(true);
    };

    const handleUpdateLibrary = async (event) => {
        event.preventDefault();
        const libraryToUpdate = {
            ...editLibrary,
            state: selectedCountry, // Send the country code as state
            city: newLibrary.city // Only send city as selected
        };
        try {
            const response = await ApiService.updateLibrary(editLibrary.id, libraryToUpdate);
            setLibraries(prevLibraries => prevLibraries.map(library => library.id === editLibrary.id ? response.data : library));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to update library:', error.response ? error.response.data : error.message);
            setError('Failed to update library.');
        }
    };

    const handleDeleteLibrary = async () => {
        try {
            await ApiService.deleteLibrary(deleteLibrary.id);
            setLibraries(prevLibraries => prevLibraries.filter(library => library.id !== deleteLibrary.id));
            handleCloseModal();
        } catch (error) {
            console.error('Failed to delete library:', error.response ? error.response.data : error.message);
            setError('Failed to delete library.');
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (deleteLibrary) {
            await handleDeleteLibrary();
        } else if (editLibrary) {
            await handleUpdateLibrary(event);
        } else {
            await handleAddLibrary(event);
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setEditLibrary(null);
        setDeleteLibrary(null);
        setNewLibrary({
            name: '',
            address: '',
            city: '',
            state: '', // Reset to empty
            zip_code: '',
            phone: '',
            email: '',
            opening_hours: ''
        });
        setStates([]);
        setCities([]);
        setSelectedCountry('');
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        if (editLibrary) {
            setEditLibrary({ ...editLibrary, [name]: value });
        } else {
            setNewLibrary({ ...newLibrary, [name]: value });
        }
    };

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-2">
                <h3>Manage Libraries</h3>
                <Button variant="btn btn-primary my-4" onClick={() => setShowModal(true)}>+ Add Library</Button>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
            <ReusableModal
                show={showModal}
                handleClose={handleCloseModal}
                handleSubmit={handleSubmit}
                title={deleteLibrary ? "Confirm Delete" : (editLibrary ? "Edit Library" : "Add New Library")}
            >
                {deleteLibrary ? (
                    <p>Are you sure you want to delete {deleteLibrary.name}?</p>
                ) : (
                    <>
                        <Form.Control
                            type="text"
                            name="name"
                            className="mb-2"
                            placeholder="Enter name"
                            value={editLibrary ? editLibrary.name : newLibrary.name}
                            onChange={handleInputChange}
                        />
                        <Form.Control
                            type="text"
                            name="address"
                            className="mb-2"
                            placeholder="Enter address"
                            value={editLibrary ? editLibrary.address : newLibrary.address}
                            onChange={handleInputChange}
                        />
                        <Form.Select
                            className="mb-2"
                            value={selectedCountry}
                            onChange={handleCountryChange}
                        >
                            <option value="">Select Country</option>
                            {Country.getAllCountries().map(country => (
                                <option key={country.isoCode} value={country.isoCode}>{country.name}</option>
                            ))}
                        </Form.Select>
                        <Form.Select
                            className="mb-2"
                            value={newLibrary.state}
                            onChange={handleStateChange}
                        >
                            <option value="">Select State</option>
                            {states.map(state => (
                                <option key={state.isoCode} value={state.isoCode}>{state.name}</option>
                            ))}
                        </Form.Select>
                        <Form.Select
                            className="mb-2"
                            value={newLibrary.city}
                            onChange={handleCityChange}
                        >
                            <option value="">Select City</option>
                            {cities.map(city => (
                                <option key={city.name} value={city.name}>{city.name}</option>
                            ))}
                        </Form.Select>
                        <Form.Control
                            type="text"
                            name="zip_code"
                            className="mb-2"
                            placeholder="Enter zip code"
                            value={editLibrary ? editLibrary.zip_code : newLibrary.zip_code}
                            onChange={handleInputChange}
                        />
                        <Form.Control
                            type="text"
                            name="phone"
                            className="mb-2"
                            placeholder="Enter phone"
                            value={editLibrary ? editLibrary.phone : newLibrary.phone}
                            onChange={handleInputChange}
                        />
                        <Form.Control
                            type="email"
                            name="email"
                            className="mb-2"
                            placeholder="Enter email"
                            value={editLibrary ? editLibrary.email : newLibrary.email}
                            onChange={handleInputChange}
                        />
                        <Form.Control
                            type="text"
                            name="opening_hours"
                            className="mb-2"
                            placeholder="Enter opening hours"
                            value={editLibrary ? editLibrary.opening_hours : newLibrary.opening_hours}
                            onChange={handleInputChange}
                        />
                    </>
                )}
            </ReusableModal>
            <Table className="table table-hover text-center mt-2">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip Code</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Opening Hours</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {libraries.map(library => (
                    <tr key={library.id}>
                        <td>{library.id}</td>
                        <td>{library.name}</td>
                        <td>{library.address}</td>
                        <td>{library.city}</td>
                        <td>{library.state}</td>
                        <td>{library.zip_code}</td>
                        <td>{library.phone}</td>
                        <td>{library.email}</td>
                        <td>{library.opening_hours}</td>
                        <td>
                            <Button variant="outline-secondary btn-sm text-dark me-2" onClick={() => handleEditClick(library)}>Edit</Button>{' '}
                            <Button variant="danger btn-sm" onClick={() => {
                                setDeleteLibrary(library);
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

export default LibrariesPage;
