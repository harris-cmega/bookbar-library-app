import React, { useContext } from 'react';
import { Container, Row, Col, Nav, Dropdown, Image } from 'react-bootstrap';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';
import useAdminAuth from '../../hooks/useAdminAuth';
import LibrariesByCityPieChart from '../dashboard/LibrariesByCityPieChart.jsx'; // Import the component
import PublishersStatsCard from '../dashboard/PublishersStatsCard';
import LibrariesStatsCard from '../dashboard/LibrariesStatsCard';
import AuthorsStatsCard from '../dashboard/AuthorsStatsCard';
import BooksStatsCard from '../dashboard/BooksStatsCard';
import {
    UserIcon,
    BookOpenIcon,
    TruckIcon,
    BuildingLibraryIcon,
    BookmarkIcon,
    UsersIcon,
    ArrowRightOnRectangleIcon,
    MagnifyingGlassIcon,
    BellIcon,
    DocumentTextIcon,
    FireIcon,
    TagIcon, // Add this line for categories icon
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
                        <Nav.Link as={Link} to="/admin/user-subscriptions" className="text-dark p-3 d-flex align-items-center">
                            <FireIcon className="icon size-4 me-2" />User Subscriptions
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/books" className="text-dark p-3 d-flex align-items-center">
                            <BookOpenIcon className="icon size-4 me-2" />Books
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/orders" className="text-dark p-3 d-flex align-items-center">
                            <TruckIcon className="icon size-4 me-2" />Orders
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/book-files" className="text-dark p-3 d-flex align-items-center">
                            <DocumentTextIcon className="icon size-4 me-2" />Book files
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
                        <Nav.Link as={Link} to="/admin/categories" className="text-dark p-3 d-flex align-items-center"> {/* Add this line */}
                            <TagIcon className="icon size-4 me-2" />Categories
                        </Nav.Link>
                        <Nav.Link as={Link} to="/admin/book-categories" className="text-dark p-3 d-flex align-items-center"> {/* Add this line */}
                            <BookOpenIcon className="icon size-4 me-2" />Book Categories
                        </Nav.Link>
                    </Nav>
                </Col>
                <Col md={10} className="px-0">
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
                        {location.pathname === '/admin/dashboard' && (
                            <>
                                <Row>
                                    <Col md={3}><AuthorsStatsCard/></Col>
                                    <Col md={3}><BooksStatsCard/></Col>
                                    <Col md={3}><PublishersStatsCard /></Col>
                                    <Col md={3}><LibrariesStatsCard/></Col>
                                </Row>
                                <Row className="mt-4">
                                    <Col md={12}><LibrariesByCityPieChart /></Col>
                                </Row>
                            </>
                        )}
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
