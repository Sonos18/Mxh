import { useState } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';


export default function AuctionDetail() {
    const [selectedDateStart, setSelectedDateStart] = useState(null);
    const [selectedDateEnd, setSelectedDateEnd] = useState(null);
    const handleDateStartChange = (date) => {
        setSelectedDateStart(date);
    };
    const handleDateEndChange = (date) => {
        setSelectedDateEnd(date);
    };
    return (
        <>
            <h1>AuctionDetail</h1>
            <p className="text-xl font-semibold mb-2">Start time</p>
            <DatePicker
                selected={selectedDateStart}
                onChange={handleDateStartChange}
                dateFormat="yyyy/MM/dd" // Customize the date format
                className="w-full p-2 border rounded-lg"
            />
            <p className="text-xl font-semibold mb-2">Start end</p>
            <DatePicker
                selected={selectedDateEnd}
                onChange={handleDateEndChange}
                dateFormat="yyyy/MM/dd" // Customize the date format
                className="w-full p-2 border rounded-lg"
            />
            <div class=" relative ">
                <label for="required-email" class="text-gray-700">
                    Email
                    <span class="text-red-500 required-dot">
                        *
                    </span>
                </label>
                <input type="text" id="required-email" class=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" name="email" placeholder="Your email" />
            </div>

            <div class=" relative ">
                <label for="startingPrice" class="text-gray-700">
                startingPrice
                    <span class="text-red-500 required-dot">
                        *
                    </span>
                </label>
                <input type="text" id="startingPrice" class=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" name="email" placeholder="Your email" />
            </div>

            <div class=" relative ">
                <label for="buyoutPrice" class="text-gray-700">
                    Email
                    <span class="text-red-500 required-dot">
                        *
                    </span>
                </label>
                <input type="text" id="buyoutPrice" class=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" name="email" placeholder="Your email" />
            </div>
            <div class=" relative ">
                <label for="winningBid" class="text-gray-700">
                    Email
                    <span class="text-red-500 required-dot">
                        *
                    </span>
                </label>
                <input type="text" id="buyoutPrice" class=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" name="email" placeholder="Your email" />
            </div>
        </>

    )
}
