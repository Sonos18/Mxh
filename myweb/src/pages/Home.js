import { useEffect, useState } from "react";
import Apis, { endpoints } from "../configs/APIS";
import PostPopup from "../component/PostPopup";
import { Link, useNavigate } from "react-router-dom";
import { MyUserContext } from "../App";
import { useContext } from "react";
import APIS from "../configs/APIS";
import Like from "../component/Like";
import Comment from "../component/Comment";

const Home = ({ searchTerm }) => {
  const [user, dispatch] = useContext(MyUserContext);
  const [posts, setPosts] = useState(null);
  const [filteredPosts, setFilteredPosts] = useState([]);
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const updatePostsList = (newPost) => {
    setFilteredPosts([newPost, ...filteredPosts]);
  }

  useEffect(() => {
    const loadPosts = async () => {
      try {
        let e = endpoints["posts"];
        let res = await Apis.get(e);
        const formattedPosts = res.data.map(post => ({
          ...post,
          createAt: new Date(post.createAt).toLocaleString(),
        }));
        setPosts(formattedPosts);
      } catch (ex) {
        console.error(ex);
      }
    };

    loadPosts();
  }, []);

  //PostPopUp
  const handleOpen = () => {
    if (user !== null)
      setIsOpen(true);
    else navigate("/login")
  }
  const handleClose = () => setIsOpen(false);


  //Filter Posts

  useEffect(() => {
    if (!searchTerm) {
      setFilteredPosts(posts);
    } else {
      const lowercaseSearchValue = searchTerm.toLowerCase();
      const filtered = posts.filter((post) =>
        post.hashtags.some((h) => h.toLowerCase().includes(lowercaseSearchValue))
      );
      setPosts(filtered);
    }
  }, [searchTerm, posts]);

  return (
    <>
      {filteredPosts &&
        filteredPosts.map(p => {
          let url = `/posts/${p.id}`;
          return <>
            <div className="mt-4 m-auto overflow-hidden rounded-lg shadow-lg cursor-pointer h-90 w-60 md:w-1/2">
              <Link to="#" class="block w-full h-full" key={p.id}>
                <div className="w-full p-4 bg-white dark:bg-gray-800 relative">
                  <div className="flex items-center mt-1">
                    <Link to="#" className="relative block">
                      <img
                        alt="profil"
                        src={p.usersDto.avatar}
                        className="mx-auto object-cover rounded-full h-10 w-10 "
                      />
                    </Link>
                    <div className="flex flex-col justify-between ml-4 text-sm">
                      <p className="text-gray-800 dark:text-white">{p.usersDto.username}</p>
                      <p className="text-gray-400 dark:text-gray-300">{p.createAt}</p>
                    </div>
                    <div className="flex items-center absolute top-2 right-4">
                      <Link to={url} className="mr-2" >
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                          className="bi bi-three-dots text-white hover:text-gray-400" viewBox="0 0 16 16">
                          <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z" />
                        </svg>
                      </Link>
                      {user !== null && p.usersDto.username === user.username ?
                        <Link to={url} className="ml-1">
                          <button className="text-white hover:text-red-700 text-2xl">
                            X
                          </button>
                        </Link> : <></>
                      }

                    </div>
                  </div>

                  <p className="font-light text-gray-400 dark:text-gray-300 text-md">
                    {p.content}
                  </p>
                  <div className="flex flex-wrap items-center mt-4 justify-starts">
                    {p.hashtags.map((h) => (
                      <div className="text-xs mr-2 py-1.5 px-4 text-gray-600 bg-blue-100 rounded-2xl">
                        {h}
                      </div>
                    ))}
                  </div>
                </div>
                <img
                  alt="blog photo"
                  src={p.file}
                  className="object-cover w-full max-h-50"
                />
                <div className="flex border-t border-gray-200 justify-center">
                  <div className="flex w-full items-center text-xl">
                    <Like like={p.like} id={p.id}/>
                    <Comment />
                  </div>
                </div>

              </Link>
            </div>
          </>
        })}
      <button onClick={handleOpen} className="fixed bottom-0 right-0 bg-blue-500 text-white px-4 py-2 rounded">Đăng bài</button>
      {isOpen && (<PostPopup handleClose={handleClose} updatePostsList={updatePostsList} />)}
    </>
  );
};
export default Home;
