import React, { useState } from "react";
import api from "../api/link";
import { useNavigate } from "react-router-dom";
import "./ForgotPasswordScreen.css";

function ForgotPasswordScreen(props) {
  //change path
  const navigate = useNavigate();

  const changePath = (path) => {
    // navigate to path
    navigate(path);
  };

  const [email, setEmail] = useState("");

  const [isSubmitted, setSubmitted] = useState(false);
  const [isEmpty, setEmpty] = useState(false);

  const [truthy, setTruthy] = useState();
  const [msg, setMsg] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitted(true);
    if (!email) {
      setEmpty(true);
    }

    const post = {
      emailId: email,
    };
    try {
      const response = await api.post("/forgot-pasword", post);
      setTruthy(false);
      console.log("-- " + JSON.stringify(response) + " --");

      if (response.status === 200) {
        changePath("/reset-email-sent");
      }
    } catch (err) {
      setTruthy(true);
      setMsg(err.response.data);
      // setMsg("Error happened");
    }
  };

  return (
    <div className="App">
      <div className="auth-form-container">
        <h2>Forgot Password</h2>
        <form className="login-form" onSubmit={handleSubmit}>
          <label htmlFor="email">Enter Email Address</label>
          <input
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            type="email"
            placeholder="example@example.com"
            id="email"
            name="email"
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

export default ForgotPasswordScreen;
