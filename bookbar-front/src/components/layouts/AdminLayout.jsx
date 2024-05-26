import React, { useContext } from 'react';
import { Container, Row, Col, Nav, Dropdown, Image } from 'react-bootstrap';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';
import useAdminAuth from '../../hooks/useAdminAuth';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSignOutAlt, faUsers, faBook, faBuilding, faAddressBook, faUserTie } from '@fortawesome/free-solid-svg-icons';
import LibrariesByCityPieChart from '../dashboard components/LibrariesByCityPieChart.jsx'; // Import the component
import PublishersStatsCard from '../dashboard components/PublishersStatsCard';
import LibrariesStatsCard from '../dashboard components/LibrariesStatsCard';
import AuthorsStatsCard from '../dashboard components/AuthorsStatsCard';
import BooksStatsCard from '../dashboard components/BooksStatsCard';
import {
    UserIcon,
    BookOpenIcon,
    BuildingLibraryIcon,
    BookmarkIcon,
    UsersIcon,
    ArrowRightOnRectangleIcon,
    MagnifyingGlassIcon,
    BellIcon
} from '@heroicons/react/24/outline';

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
                <Col md={2} className="bg-white border-end min-vh-100 p-0">
                    <div className="pt-4 pb-3 px-3">
                        <p className="text-muted">Bookbar Dashboard</p>
                    </div>
                    <Nav className="flex-column">
                        <Nav.Link as={Link} to="/admin/users" className="text-dark p-3 d-flex align-items-center">
                            <UsersIcon className="icon size-4 me-2" />Users
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/books" className="text-dark p-3 d-flex align-items-center">
                            <BookOpenIcon className="icon size-4 me-2" />Books
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/libraries" className="text-dark p-3 d-flex align-items-center">
                            <BuildingLibraryIcon className="icon size-4 me-2" />Libraries
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/publishers" className="text-dark p-3 d-flex align-items-center">
                            <BookmarkIcon className="icon size-4 me-2" />Publishers
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/authors" className="text-dark p-3 d-flex align-items-center">
                            <UserIcon className="icon size-4 me-2" />Authors
                        </Nav.Link>
                    </Nav>
                </Col>
                <Col md={10} className="px-0">
                    {location.pathname === '/admin/dashboard' && (
                        <>
                            <Row>
                                <Col md={3}><AuthorsStatsCard/></Col>
                                <Col md={3}><BooksStatsCard/></Col>
                                <Col md={3}><PublishersStatsCard /></Col>
                                <Col md={3}><LibrariesStatsCard/></Col>
                            </Row>
                            <Row>
                                <Col md={12}><LibrariesByCityPieChart /></Col>
                            </Row>
                        </>
                    )}
                    <div className="d-flex justify-content-between align-items-center p-3 border-bottom">
                        <div className="d-flex align-items-center">
                            <MagnifyingGlassIcon className="icon size-5 me-3" />
                            <BellIcon className="icon size-5" />
                        </div>
                        <Dropdown align="end">
                            <Dropdown.Toggle variant="link" id="dropdown-basic" className="d-flex align-items-center">
                                <Image
                                    src="https://via.placeholder.com/40"
                                    roundedCircle
                                    width="40"
                                    height="40"
                                    className="me-2"
                                />
                                <span>{user?.sub}</span>
                            </Dropdown.Toggle>
                            <Dropdown.Menu>
                                <Dropdown.Item onClick={handleLogout}>
                                    <ArrowRightOnRectangleIcon className="icon me-2" /> Logout
                                </Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                    </div>
                    <div className="p-4">
                        <Outlet/>
                    </div>
                </Col>
            </Row>
        </Container>
    );
};

export default AdminLayout;
