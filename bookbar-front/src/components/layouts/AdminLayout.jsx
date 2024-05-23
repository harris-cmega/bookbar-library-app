import React, { useContext } from 'react';
import { Container, Row, Col, Nav } from 'react-bootstrap';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';
import useAdminAuth from '../../hooks/useAdminAuth';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSignOutAlt, faUsers, faBook, faBuilding, faAddressBook, faUserTie } from '@fortawesome/free-solid-svg-icons';

const AdminLayout = () => {
    const { user, logout } = useContext(AuthContext);
    const navigate = useNavigate();
    useAdminAuth();

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    return (
        <Container fluid className="bg-light min-vh-100">
            <Row>
                <Col md={2} className="bg-primary text-white min-vh-100">
                    <div className="py-3 px-3">
                        <h4>{user?.sub + " Admin Dashboard"}</h4>
                    </div>
                    <Nav className="flex-column">
                        <Nav.Link as={Link} to="/admin/users" className="text-white">
                            <FontAwesomeIcon icon={faUsers}/> Manage Users
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/books" className="text-white">
                            <FontAwesomeIcon icon={faBook}/> Manage Books
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/libraries" className="text-white">
                            <FontAwesomeIcon icon={faBuilding}/> Manage Libraries
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/publishers" className="text-white">
                            <FontAwesomeIcon icon={faAddressBook}/> Manage Publishers
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/authors" className="text-white">
                            <FontAwesomeIcon icon={faUserTie}/> Manage Authors
                        </Nav.Link>
                    </Nav>
                    <button className="btn btn-danger mt-3" onClick={handleLogout}>
                        <FontAwesomeIcon icon={faSignOutAlt}/> Logout
                    </button>
                </Col>
                <Col md={10} className="p-4">
                    <Outlet/>
                </Col>
            </Row>
        </Container>
    );
};

export default AdminLayout;