import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const useAccount = () => {
    const [account, setAccount] = useState(null);
    const navigate = useNavigate();

    const fetchAccount = async () => {
        try {
            const response = await axios.get("http://localhost:8088/accounts/1", {
                headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
            });
            setAccount(response.data);
        } catch (error) {
            console.error("Error fetching account:", error);
        }
    };

    const logout = () => {
        localStorage.removeItem("token");
        navigate("/");
    };

    useEffect(() => {
        fetchAccount();
    }, []);

    return { account, fetchAccount, logout };
};

export default useAccount;