import React from "react";
import { useNavigate } from "react-router-dom";
import logo from "../logo.png";
import "./ResetEmailSentScreen.css";

function ResetEmailSentScreen() {
  //change path
  const navigate = useNavigate();
  const changePath = (path) => {
    navigate(path);
  };

  return (
    <div className="App">
      <div className="auth-form-container">
        <div className="logo">
          <img src={logo} alt="logo" />
          <h1>Markata</h1>
        </div>
        <h2>We have sent you a reset password link to you email address. 😁</h2>
        <h3>Please check your email to set you new password</h3>
        <p>
          Don't have an account?{" "}
          <span className="link-btn" onClick={() => changePath("/signup")}>
            Signup here
          </span>
        </p>
      </div>
    </div>
  );
}

export default ResetEmailSentScreen;
