import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "socialnetwork/";
const SERVER = "http://localhost:8080/";

export const endpoints = {
    "posts": `${SERVER_CONTEXT}api/posts/`,
    "login": `${SERVER_CONTEXT}api/login/`,
    "current-user":`${SERVER_CONTEXT}api/current-user/`,
    "register":`${SERVER_CONTEXT}api/register/`,
    "post-detail":(id)=>`${SERVER_CONTEXT}api/posts/${id}/`,
    "notification":`${SERVER_CONTEXT}api/users/notifications/`,
    "read":(id)=>`${SERVER_CONTEXT}api/users/notifications/${id}/`,
    "like":(id)=>`${SERVER_CONTEXT}api/posts/${id}/like/`,

}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization": cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
})