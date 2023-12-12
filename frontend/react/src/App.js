import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ErrorBoundary from "./components/ErrorBoundary";
import LoginScreen from "./screens/LoginScreen";
import SignupScreen from "./screens/SignupScreen";
import HomeScreen from "./screens/HomeScreen";
import UserActivateScreen from "./screens/UserActivateScreen";
import SuccessVerificationPage from "./screens/SuccessVerificationPage";
import ResetPasswordScreen from "./screens/ResetPasswordScreen";
import ForgotPasswordScreen from "./screens/ForgotPasswordScreen"
import ResetEmailSentScreen from "./screens/ResetEmailSentScreen";

function App() {
  return (
    <div>
      <Router>
        <ErrorBoundary>
          <Routes>
            <Route path="/" element={<HomeScreen />} />
            <Route path="/login" element={<LoginScreen />} />
            <Route path="/signup" element={<SignupScreen />} />

            <Route
              path="/users/activate-account"
              element={<UserActivateScreen />}
            />
            <Route
              path="/success-verification-page"
              element={<SuccessVerificationPage />}
            />
            <Route path="/forgot-password" element={<ForgotPasswordScreen />} />
            <Route path="/reset-password" element={<ResetPasswordScreen />} />
            <Route path="/reset-email-sent" element={<ResetEmailSentScreen />} />
          </Routes>
        </ErrorBoundary>
      </Router>
    </div>
  );
}

export default App;

