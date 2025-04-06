import React from "react";
import useAccount from "../../hooks/useAccount";
import './AccountDetails.css'

const AccountDetails = () => {

    const { account } = useAccount();


    return (
        <>
            <h1 className="home-title">
                Welcome, {account ? account.ownerName : "Loading..."}
            </h1>
            <p className="home-balance">Balance: ${account ? account.balance : "Loading..."}</p>
        </>
    );
};

export default AccountDetails;