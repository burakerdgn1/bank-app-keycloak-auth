import { useState } from "react";

const useMessage = () => {
    const [message, setMessage] = useState("");
    const [messageType, setMessageType] = useState("");

    const showMessage = (msg, type) => {
        setMessage(msg);
        setMessageType(type);
        setTimeout(() => setMessage(""), 3000);
    };

    return { message, messageType, showMessage };
};

export default useMessage;