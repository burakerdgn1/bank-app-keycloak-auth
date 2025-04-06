import React from "react";
import bankLogo from "../../bank.jpg";
import './SignInForm.css';
import useKeycloakAuth from "../../hooks/useKeycloakAuth";

const SignInForm = () => {
    const { initiateLogin } = useKeycloakAuth();

    return (
        <div className="signin-box">
            <img src={bankLogo} alt="Bank Logo" className="signin-logo" />
            <h1 className="signin-title">Welcome to Bank App</h1>
            <p className="signin-subtitle">Manage your finances securely and efficiently</p>
            <button className="signin-button" onClick={initiateLogin}>
                Sign in with Keycloak
            </button>
        </div>
    );
};

export default SignInForm;
