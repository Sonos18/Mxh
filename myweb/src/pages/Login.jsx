import { useEffect } from "react";

const Login = () => {
  useEffect(() => {
    // Ẩn Header và Footer khi ở trang đăng nhập
    document.querySelector("header").style.display = "none";
    document.querySelector("footer").style.display = "none";

    return () => {
      // Hiển thị lại Header và Footer khi rời khỏi trang đăng nhập
      document.querySelector("header").style.display = "block";
      document.querySelector("footer").style.display = "block";
    };
  }, []);

  return (
    <div>
     Login
    </div>
  );
};

export default Login;




