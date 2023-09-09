import React from 'react';

function Sidebar() {
  return (
    <div className="relative hidden h-screen my-4 ml-4 shadow-lg lg:block w-80">
      <div className="h-full bg-white rounded-2xl dark:bg-gray-700">
        <div className="flex items-center justify-center pt-6">
          <svg width="35" height="30" viewBox="0 0 256 366" version="1.1" preserveAspectRatio="xMidYMid">
            {/* ... */}
          </svg>
        </div>
        <nav className="mt-6">
          <div>
            <a className="flex items-center justify-start w-full p-4 my-2 font-thin text-blue-500 uppercase transition-colors duration-200 border-r-4 border-blue-500 bg-gradient-to-r from-white to-blue-100 dark:from-gray-700 dark:to-gray-800" href="#">
              <span className="text-left">
                <svg width="20" height="20" fill="currentColor" viewBox="0 0 2048 1792" xmlns="http://www.w3.org/2000/svg">
                  {/* ... */}
                </svg>
              </span>
              <span className="mx-4 text-sm font-normal">
                Dashboard
              </span>
            </a>
            {/* ... Add more menu items here */}
          </div>
        </nav>
      </div>
    </div>
  );
}

export default Sidebar;
