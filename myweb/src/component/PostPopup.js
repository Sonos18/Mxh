import React, { useEffect, useRef, useState } from 'react';
import APIS, { authApi, endpoints } from '../configs/APIS';
import { useNavigate, useParams } from 'react-router-dom';
import Loading from './Loading';
import { toast } from 'react-toastify';

const PostPopup = (props) => {
    const imgFile = useRef();
    const [err, setErr] = useState(null);
    let nav = useNavigate();
    const { id } = useParams();
    const postId = id || null;
    const [isLoading, setIsLoading] = useState(false);
    const handleContentChange = (e) => setContentTamp(e.target.value);
    const [contentTamp, setContentTamp] = useState('');
    useEffect(() => {
        if (postId !== null) {
            const loadPost = async () => {
                try {
                    let { data } = await APIS.get(endpoints['post-detail'](id));
                    setContentTamp(data.content + "\n");
                    if (data.hashtags)
                        data.hashtags.map(h => {
                            setContentTamp((content) => content + h + " ");
                        })
                }
                catch (ex) {
                    toast.error("Failed")
                }
            };
            loadPost();
        }
    }, []);
    const handleClose = () => nav("/");
    const handleSubmit = (e) => {
        e.preventDefault();
        setIsLoading(true);
        const process = async () => {
            let formData = new FormData();
            const hashtags = contentTamp.match(/#\w+\b/g);
            const content = contentTamp.replace(/#\w+\b/g, '');
            formData.append("content", content);
            formData.append("hashtags", JSON.stringify(hashtags || [null]));
            formData.append("imgFile", imgFile.current.files[0] || null);
            //Thêm khi postId= null
            try {
                if (postId === null) {
                    let res = await authApi().post(endpoints['posts'], formData);
                    if (res.status === 201) {
                        props.updatePostsList(res.data);
                        toast.success("Create Post Successful");
                        props.handleClose();
                        nav("/");
                    }
                    else toast.error("Failed");
                    //Update khi có id
                } else {
                    formData.append("id", id);
                    let res = await authApi().post(endpoints['post-detail'](id), formData);
                    if (res.status === 200) {
                        toast.success("Update Post successfull");
                        nav("/");
                    }
                    else toast.error("Failed");
                }
            } catch (ex) {
                setIsLoading(false);
                toast.error("Bad Request");
            }

            setIsLoading(false);
        }
        process();
    };

    //Xóa
    const handleDelete = () => {
        const process = async () => {
            try {
                await toast.promise(
                    new Promise((resolve, reject) => {
                        const confirm = window.confirm("Are you sure delete this Post");
                        if (confirm) {
                            resolve();
                        } else {
                            reject();
                        }
                    }),
                    {
                        pending: "Đang xác nhận...",
                        success: "Xóa thành công!",
                        error: "Lỗi xảy ra khi xóa!"
                    }
                );

                let formData = new FormData();
                setIsLoading(true);
                let res = await authApi().delete(endpoints['post-detail'](id), formData);
                setIsLoading(false);
                nav("/");
            } catch (error) {
                console.error(error);
            }
        };
        process();
    };







    return (
        <>
            <div className="fixed z-10 inset-0 overflow-y-auto">
                <div className="flex items-end justify-center min-h-screen pt-4 px-2 pb-20 text-center sm:block sm:p-0">
                    <div className="fixed inset-0 transition-opacity" aria-hidden="true">
                        <div className="absolute inset-0 bg-gray-500 opacity-75"></div>
                    </div>
                    <span className="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
                    <div className="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
                        <div className="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
                            <div className="sm:flex sm:items-start">
                                <div className="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
                                    <h3 className="text-lg leading-6 font-medium text-gray-900" id="modal-title">
                                        {postId === null ? 'CreatePost' : 'UpdatePost'}
                                    </h3>
                                    {err === null ? "" : <alert className="text-red-500">{err}</alert>}
                                    <div className="mt-2">
                                        <textarea onChange={handleContentChange} value={contentTamp} className="w-full px-3 py-2 text-gray-700 border rounded-lg focus:outline-none" rows="5" placeholder="Nội dung..."></textarea>
                                        <input type="file" className="mt-2" ref={imgFile} />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
                            {postId === null ?
                                <>
                                    <button onClick={handleSubmit} type="button" className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-blue-500 text-base font-medium text-white hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 sm:ml-3 sm:w-auto sm:text-sm">
                                        Create
                                    </button>
                                    <button onClick={props.handleClose} type="button" className="hover:bg-red-700 text-white bg-red-500 mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 text-base font-medium focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm">
                                        Cancel
                                    </button>
                                </> :
                                <>
                                    <button onClick={handleSubmit} type="button" className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-blue-500 text-base font-medium text-white hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 sm:ml-3 sm:w-auto sm:text-sm">
                                        Update
                                    </button>
                                    <button onClick={handleDelete} type="button" className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-500 text-base font-medium text-white hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 sm:ml-3 sm:w-auto sm:text-sm">
                                        Delete
                                    </button>
                                    <button onClick={handleClose} type="button" className="hover:bg-yellow-700 text-white bg-yellow-500 mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 text-base font-medium focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm">
                                        Cancel
                                    </button></>
                            }
                        </div>
                    </div>
                </div>
            </div>
            {isLoading ? <Loading /> : null}
        </>
    );
};

export default PostPopup;