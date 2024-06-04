import React from "react";
import { useQuery } from "@tanstack/react-query";
import axios from "axios";
import { List, ListItem, ListItemText } from "@mui/material";

const fetchClassesByDramaId = async (dramaId: string) => {
    const response = await axios.get(`http://localhost:8080/api/dramas/${dramaId}/classes`);
    return response.data;
};

interface ClassOfDramalistProps {
    dramaId: string;
}

const ClassOfDramalist: React.FC<ClassOfDramalistProps> = ({ dramaId }) => {
    const { data: classes, isLoading, error } = useQuery(["classes", dramaId], () => fetchClassesByDramaId(dramaId));

    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error loading classes</div>;

    return (
        <List>
            {classes.map((className: string) => (
                <ListItem key={className}>
                    <ListItemText primary={className} />
                </ListItem>
            ))}
        </List>
    );
};

export default ClassOfDramalist;
