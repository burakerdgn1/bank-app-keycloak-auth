import { useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";

const useKeycloakAuth = () => {
    const navigate = useNavigate();
    const location = useLocation();

    const redirectUri = encodeURIComponent("http://localhost:3000/home");
    const keycloakAuthUrl = `http://localhost:7080/realms/bank-app/protocol/openid-connect/auth?client_id=gatewayserver&response_type=code&redirect_uri=${redirectUri}`;

    // Function to start login process
    const initiateLogin = () => {
        window.location.href = keycloakAuthUrl;
    };

    // Function to handle post-login redirect and code processing
    useEffect(() => {
        const params = new URLSearchParams(location.search);
        const code = params.get("code");

        if (code) {
            // TODO: Exchange the code for an access token here (usually via backend API)
            console.log("Authorization Code:", code);

            // Simulate successful authentication and navigate to the home page
            navigate("/home");
        }
    }, [location, navigate]);

    return {
        initiateLogin,
    };
};

export default useKeycloakAuth;
