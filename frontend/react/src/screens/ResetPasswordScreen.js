import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import api from "../api/link";
import "./ResetPasswordScreen.css";

function ResetPasswordScreen(props) {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  // Access specific query parameters
  const token = searchParams.get("token");

  //change path
  const navigate = useNavigate();
  const changePath = (path) => {
    // navigate to path
    navigate(path);
  };

  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [isSubmitted, setSubmitted] = useState(false);
  const [isEmpty, setEmpty] = useState(false);

  const [truthy, setTruthy] = useState();
  const [msg, setMsg] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitted(true);
    if (!password) {
      setEmpty(true);
    }
    console.log(password);

    const post = {
      token: token,
      newPassword: password
    };
    if (password === confirmPassword) {
      try {
        const response = await api.post("/reset-password", post);
        setTruthy(false);
        console.log("-- " + JSON.stringify(response) + " --");

        if (response.status === 200) {
          changePath("/login");
        }
      } catch (err) {
        setTruthy(true);
        //   setMsg(err.response.data);
        setMsg("Error happened");
      }
    } else {
      alert("Passwords do not match");
    }
  };




  return (
    <div className="App">
      <div className="auth-form-container">
        <h2>Reset Password</h2>
        <form className="login-form" onSubmit={handleSubmit}>
          <label htmlFor="password"> New Password</label>
          <input
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            type="password"
            placeholder="Enter password"
            id="new-password"
            name="password"
            required
            className={isEmpty && isSubmitted ? "error" : ""}
          />
          <label htmlFor="password">Confirm Password</label>
          <input
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            type="password"
            placeholder="Confirm password"
            id="confirm-password"
            name="password"
            required
            className={isEmpty && isSubmitted ? "error" : ""}
          />

          <button type="submit">Send</button>
        </form>
        <p>
          Don't have an account?{" "}
          <span className="link-btn" onClick={() => changePath("/signup")}>
            Signup here
          </span>
        </p>
        {truthy ? <p style={{ color: "red" }}>{`${msg}`}</p> : <p></p>}
      </div>
    </div>
  );
}

export default ResetPasswordScreen;
