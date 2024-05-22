import React from 'react';
import { Link, Outlet } from 'react-router-dom';
import { Container, Row, Col, Nav } from 'react-bootstrap';
import useAdminAuth from '../../hooks/useAdminAuth.js';

const AdminLayout = () => {
    useAdminAuth();

    return (
        <Container fluid>
            <Row>
                <Col md={2}>
                    <Nav className="flex-column">
                        <Nav.Link as={Link} to="/admin/users">Manage Users</Nav.Link>
                        <Nav.Link as={Link} to="/admin/books">Manage Books</Nav.Link>
                        <Nav.Link as={Link} to="/admin/libraries">Manage Libraries</Nav.Link>
                        <Nav.Link as={Link} to="/admin/publishers">Manage Publishers</Nav.Link>
                        <Nav.Link as={Link} to="/admin/authors">Manage Authors</Nav.Link>
                    </Nav>
                </Col>
                <Col md={10}>
                    <Outlet />
                </Col>
            </Row>
        </Container>
    );
};

export default AdminLayout;
