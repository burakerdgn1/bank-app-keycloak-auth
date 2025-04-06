import React from "react";
import useAccount from "../../hooks/useAccount";
import './LogoutButton.css'

const LogoutButton = () => {
    const { logout } = useAccount();

    return (
        <button className="logout-button" onClick={logout}>
            Logout
        </button>
    );
};

export default LogoutButton;