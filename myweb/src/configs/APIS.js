import axios from "axios";

const SERVER_CONTEXT = "socialnetwork/";
const SERVER = "http://localhost:8080/";
export const endpoints = {
    "posts":  `${SERVER_CONTEXT}api/posts/`
}

export default axios.create({
    baseURL: SERVER
})