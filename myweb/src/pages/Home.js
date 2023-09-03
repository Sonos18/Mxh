import { useEffect, useState } from "react";
import Apis, { endpoints } from "../configs/APIS";
import PostPopup from "../component/PostPopup";

const Home = ({searchTerm }) => {
  const [posts, setPosts] = useState(null);
  const [filteredPosts, setFilteredPosts] = useState([]);
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

  //Filter Posts

  useEffect(() => {
    if (!searchTerm) {
      setFilteredPosts(posts);
    } else {
      const lowercaseSearchValue = searchTerm.toLowerCase();
      const filtered = posts.filter((post) =>
        post.hashtags.some((h) => h.toLowerCase().includes(lowercaseSearchValue))
      );
      setFilteredPosts(filtered);
    }
  }, [searchTerm, posts]);

  return (
    <>
      {filteredPosts &&
        filteredPosts.map((p) => (
          <div className="mt-4 m-auto overflow-hidden rounded-lg shadow-lg cursor-pointer h-90 w-60 md:w-3/5">
            <a href="#" class="block w-full h-full">
              <img
                alt="blog photo"
                src={p.file}
                className="object-cover w-full max-h-40"
              />

              <div className="w-full p-4 bg-white dark:bg-gray-800">
                <p className="font-medium text-indigo-500 text-md">Article</p>
                <p className="mb-2 text-xl font-medium text-gray-800 dark:text-white">
                  Supercharged !
                </p>
                <p className="font-light text-gray-400 dark:text-gray-300 text-md">
                  {p.content}
                </p>
                <div className="flex flex-wrap items-center mt-4 justify-starts">
                  {p.hashtags.map((h)=>(
                      <div className="text-xs mr-2 py-1.5 px-4 text-gray-600 bg-blue-100 rounded-2xl">
                        #{h}
                      </div>
                  ))}
                  
                  
                </div>
                <div className="flex items-center mt-4">
                  <a href="#" className="relative block">
                    <img
                      alt="profil"
                      src="/images/person/6.jpg"
                      className="mx-auto object-cover rounded-full h-10 w-10 "
                    />
                  </a>
                  <div className="flex flex-col justify-between ml-4 text-sm">
                    <p className="text-gray-800 dark:text-white"></p>
                    <p className="text-gray-400 dark:text-gray-300">{p.createAt}</p>
                  </div>
                </div>

              </div>
            </a>
          </div>
        ))}
        <PostPopup/>
    </>
  );
};
export default Home;
