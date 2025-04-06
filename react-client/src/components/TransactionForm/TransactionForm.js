import React from "react";
import useTransaction from "../../hooks/useTransaction";
import useMessage from "../../hooks/useMessage";
import './TransactionForm.css'

const TransactionForm = () => {
    const { account } = useTransaction();
    const { amount, setAmount, handleTransaction } = useTransaction(account);
    const { message, messageType } = useMessage();

    return (
        <>
            {message && <div className={`message ${messageType}`}>{message}</div>}

            <input
                type="number"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
                placeholder="Enter amount"
                className="amount-input"
            />

            <div className="button-group">
                <button className="deposit-button" onClick={() => handleTransaction("deposit")}>
                    Deposit
                </button>
                <button className="withdraw-button" onClick={() => handleTransaction("withdraw")}>
                    Withdraw
                </button>
            </div>
        </>
    );
};

export default TransactionForm;