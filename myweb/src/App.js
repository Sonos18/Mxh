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
import Auction from './pages/Auction';
import PostPopup from './component/PostPopup';
import AuctionDetail from './pages/AuctionDetail';
import InputPrice from './component/InputPricePopup';
import ChoseWinnerPopUp from './component/ChoseWinnerPopUp';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import ReportPopUp from './component/ReportPopUp';

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
            <Route path="/posts/:id" element={<PostPopup/>}/>
            <Route path="/auction" element={<Auction/>}/>
            <Route path="/auction/auctiondetail" element={<AuctionDetail/>}/>
            <Route path="/auctions/:id/winningBid"element={<InputPrice/>}/>
            <Route path="/auctions/:id/choseWinner"element={<ChoseWinnerPopUp/>}/>
            <Route path="/posts/:id/report" element={<ReportPopUp/>}/>
          </Routes>
        </div>
        <Footer />
       <ToastContainer />
      </BrowserRouter>
    </MyUserContext.Provider>
  );
}

export default App;
