import logo from './logo.svg';
import './App.css';
import Header from './layout/Header';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import Home from './pages/Home';
import Footer from './layout/Footer';
import About from './pages/About';
import Login from './pages/Login';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <div>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About/>}/>
          <Route path="/login" element={<Login />} />

        </Routes>
      </div>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
