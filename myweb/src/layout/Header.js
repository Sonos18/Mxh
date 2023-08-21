import { useState } from "react";
import { Link } from "react-router-dom";

const Header = () => {
  const [activeLink, setActiveLink] = useState(0);

  const handleLinkClick = (index) => {
    setActiveLink(index);
  };

  const links = [
    { text: "Home", link: "/" },
    { text: "Logs", link: "#" },
    { text: "Auctions", link: "#" },
    { text: "About", link: "/about" },
    { text: "Login", link: "/login" },
  ];
  return (
    <>
      <div className="border-gray-200 border-b py-3">
        <div className="flex items-center justify-between">
        {/* Logo */}
          <div className="flex items-center space-x-2 ml-4">
            Logo
          </div>
          {/* Navigation */}
          <div className="text-sm font-medium text-center text-gray-500 border-gray-200">
            <ul className="flex flex-wrap -mb-px">
              {links.map((link, index) => (
                <li className="mr-2" key={index}>
                  <Link
                    to={link.link}
                    className={`inline-block p-4 ${
                      activeLink === index
                        ? "text-purple-600 border-b-2 border-purple-600 rounded-t-lg active"
                        : "border-b-2 border-transparent rounded-t-lg hover:text-gray-600 hover:border-gray-300"
                    }`}
                    onClick={() => handleLinkClick(index)}
                  >
                    {link.text}
                  </Link>
                </li>
              ))}
            </ul>
          </div>
          <div class="text-end mt-1">
            <form class="flex flex-col justify-center w-3/4 max-w-sm space-y-3 md:flex-row md:w-full md:space-x-3 md:space-y-0">
              <div class=" relative ">
                <input
                  type="text"
                  id='"form-subscribe-Search'
                  class=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                  placeholder="Enter a title"
                />
              </div>
              <button
                class="flex-shrink-0 px-4 py-2 text-base font-semibold text-white bg-purple-600 rounded-lg shadow-md hover:bg-purple-700 focus:outline-none focus:ring-2 focus:ring-purple-500 focus:ring-offset-2 focus:ring-offset-purple-200"
                type="submit"
              >
                Search
              </button>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};
export default Header;
