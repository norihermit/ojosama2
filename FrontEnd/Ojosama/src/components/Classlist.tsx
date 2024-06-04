import axios from "axios";
import { useQuery } from "@tanstack/react-query";

const fetchClasses = async () => {
    const response = await axios.get("http://localhost:8080/api/classes");
    return response.data;
};

export const useClassList = () => {
    return useQuery("classes", fetchClasses);
};
