import Header from "../layout/Header";
import { useEffect, useState } from "react";
import Apis, { endpoints } from "../configs/APIS";

const Home = () => {
  const [posts, setPosts] = useState(null);
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

  return (
    <>
      {posts &&
        posts.map((p) => (
          <div class="mt-4 m-auto overflow-hidden rounded-lg shadow-lg cursor-pointer h-90 w-60 md:w-80">
            <a href="#" class="block w-full h-full">
              <img
                alt="blog photo"
                src={p.file}
                class="object-cover w-full max-h-40"
              />
              <div class="w-full p-4 bg-white dark:bg-gray-800">
                <p class="font-medium text-indigo-500 text-md">Article</p>
                <p class="mb-2 text-xl font-medium text-gray-800 dark:text-white">
                  Supercharged !
                </p>
                <p class="font-light text-gray-400 dark:text-gray-300 text-md">
                  {p.content}
                </p>
                <div class="flex items-center mt-4">
                  <a href="#" class="relative block">
                    <img
                      alt="profil"
                      src="/images/person/6.jpg"
                      class="mx-auto object-cover rounded-full h-10 w-10 "
                    />
                  </a>
                  <div class="flex flex-col justify-between ml-4 text-sm">
                    <p class="text-gray-800 dark:text-white"></p>
                    <p class="text-gray-400 dark:text-gray-300">{p.createAt}</p>
                  </div>
                </div>
              </div>
            </a>
          </div>
        ))}
    </>
  );
};
export default Home;
