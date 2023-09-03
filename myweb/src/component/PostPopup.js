import React, { useEffect, useRef, useState } from 'react';
import APIS, { authApi, endpoints } from '../configs/APIS';
import { useNavigate } from 'react-router-dom';

const PostPopup = () => {
    const imgFile = useRef();
    const [isOpen, setIsOpen] = useState(false);
    const [contentTamp, setContentTamp] = useState('');
    const [err, setErr] = useState(null);
    let nav = useNavigate();
    const handleOpen = () => setIsOpen(true);
    const handleClose = () => setIsOpen(false);

    const handleContentChange = (e) => setContentTamp(e.target.value);
    const handleSubmit = (e) => {
        e.preventDefault();
        const process = async () => {
            let formData = new FormData();
            const hashtags=contentTamp.match(/#\w+\b/g);
            const content=contentTamp.replace(/#\w+\b/g,'');
            formData.append("content", content);
            formData.append("hashtags", JSON.stringify(hashtags||[]));
            formData.append("imgFile", imgFile.current.files[0]);
            console.info(content);
            console.info(hashtags);
            console.info(formData);
            let res = await authApi().post(endpoints['posts'],formData);
            if(res.status===201)
                nav("/");
            else setErr("Failed");
        }
        process();
    };

    return (
        <div>
            <button onClick={handleOpen} className="fixed bottom-0 right-0 bg-blue-500 text-white px-4 py-2 rounded">Đăng bài</button>
            {isOpen && (
                <div className="fixed z-10 inset-0 overflow-y-auto">
                    <div className="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
                        <div className="fixed inset-0 transition-opacity" aria-hidden="true">
                            <div className="absolute inset-0 bg-gray-500 opacity-75"></div>
                        </div>
                        <span className="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
                        <div className="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
                            <div className="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
                                <div className="sm:flex sm:items-start">
                                    <div className="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
                                        <h3 className="text-lg leading-6 font-medium text-gray-900" id="modal-title">
                                            Đăng bài
                                        </h3>
                                        {err === null?"":<alert className="text-red-500">{err}</alert>}
                                        <div className="mt-2">
                                            <textarea onChange={handleContentChange} value={contentTamp} className="w-full px-3 py-2 text-gray-700 border rounded-lg focus:outline-none" rows="4" placeholder="Nội dung..."></textarea>
                                            <input type="file" className="mt-2" ref={imgFile} />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
                                <button onClick={handleSubmit} type="button" className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-blue-600 text-base font-medium text-white hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 sm:ml-3 sm:w-auto sm:text-sm">
                                    Đăng
                                </button>
                                <button onClick={handleClose} type="button" className="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm">
                                    Hủy
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default PostPopup;