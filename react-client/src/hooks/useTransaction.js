import { useState } from "react";
import axios from "axios";
import useMessage from "./useMessage";

const useTransaction = (account, fetchAccount) => {
    const [amount, setAmount] = useState("");
    const { showMessage } = useMessage();

    const handleTransaction = async (type) => {
        if (!amount || isNaN(amount) || parseFloat(amount) <= 0) {
            showMessage("Please enter a valid amount.", "error");
            return;
        }

        if (type === "withdraw" && parseFloat(amount) > account.balance) {
            showMessage("There is not enough balance to withdraw.", "error");
            return;
        }

        const newBalance =
            type === "deposit"
                ? account.balance + parseFloat(amount)
                : account.balance - parseFloat(amount);

        try {
            await axios.put(
                `http://localhost:8088/accounts/${account.id}/update-balance`,
                { newBalance },
                { headers: { Authorization: `Bearer ${localStorage.getItem("token")}` } }
            );
            fetchAccount();
            setAmount("");
            showMessage(`Money ${type}ed successfully.`, "success");
        } catch (error) {
            console.error("Transaction error:", error);
            showMessage("Transaction failed. Please try again.", "error");
        }
    };

    return { amount, setAmount, handleTransaction };
};

export default useTransaction;