import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import Department from '../departments/Departments';
import Users from '../pages/Users';
import NoPage from "../pages/NoPage";
import Home from "../pages/Home";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AddUser from '../users/AddUser';
import ViewUser from '../users/ViewUser';
import AddDepartment from '../departments/AddDepartment';

function Example() {
  return (
    <>
    <BrowserRouter>
      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="/">LTSNU Salary</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link href="/departments">Відділи</Nav.Link>
            <Nav.Link href="/users">Співробітники</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <Routes>
          <Route index element={<Home />} />
          <Route path="departments" element={<Department />} />
          <Route path="users" element={<Users />} />
          <Route path="*" element={<NoPage />} />
          <Route path="viewuser" element={<ViewUser />} />
          <Route path="adduser" element={<AddUser />} />
          <Route path='adddepartment' element={<AddDepartment /> } />
      </Routes>
  </BrowserRouter>
    </>
  );
}

export default Example;