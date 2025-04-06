import React from "react";
import AccountDetails from "../../components/AccountDetails/AccountDetails";
import TransactionForm from "../../components/TransactionForm/TransactionForm";
import LogoutButton from "../../components/LogoutButton/LogoutButton";
import "./HomePage.css";

const HomePage = () => {
    return (
        <div className="home-container">
            <div className="home-box">
                <AccountDetails />
                <TransactionForm />
                <LogoutButton />
            </div>
        </div>
    );
};

export default HomePage;