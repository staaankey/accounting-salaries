import './App.css';

import { BrowserRouter, Routes, Route } from "react-router-dom";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Navbar from './layout/Navbar';

import Department from './pages/Departments';
import Users from './pages/Users';
import Layout from "./pages/Layout";
import Home from "./pages/Home";
import NoPage from "./pages/NoPage";



function App() {
  return (
    <Navbar/>
  );
}


export default App;
