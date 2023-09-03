import './App.css';
import Header from './layout/Header';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Footer from './layout/Footer';
import About from './pages/About';
import Login from './pages/Login';
import { createContext, useReducer, useState } from 'react';
import MyUserReducer from './reducers/MyUserReducer';
import cookie from 'react-cookies';
import Register from './pages/Register';

export const MyUserContext = createContext();

function App() {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);
  //Search Posts 
  const [searchTerm, setSearchTerm] = useState("");
  const handleSearchChange = (newSearchTerm) => {
    setSearchTerm(newSearchTerm);
  };
  return (
    <MyUserContext.Provider value={[user, dispatch]}>
      <BrowserRouter>
        <Header onSearchChange={handleSearchChange} />
        <div>
          <Routes>
            <Route path="/" element={<Home searchTerm={searchTerm} />} />
            <Route path="/about" element={<About />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register/>}/>

          </Routes>
        </div>
        <Footer />
      </BrowserRouter>
    </MyUserContext.Provider>
  );
}

export default App;
