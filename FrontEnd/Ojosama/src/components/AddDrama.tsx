import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import React, { useState } from "react";
import { useQueryClient, useMutation } from "@tanstack/react-query";
import { addDrama } from '../api/videoapi';
import DramaDialogContent from "./DramaDialogContent";
import { Select, MenuItem, InputLabel, FormControl, Chip, Checkbox, ListItemText } from "@mui/material";
import { useClassList } from "./Classlist";

function AddDrama() {
const [open, setOpen] = useState(false);

const handleClickOpen = () => {
    setOpen(true);
};

const handleClose = () => {
    setOpen(false);
};

const handleChange = (event : React.ChangeEvent<HTMLInputElement>) =>
    {setDrama({...drama, [event.target.name]:event.target.value});}

const handleSave = () => {
    mutate({ ...drama, classes: selectedClasses });
    setDrama({ dramaName: '', dramaCountry: '', dramaIntro: '', dramaYear: 0, dramaEpisode: 0 });
    setSelectedClasses([]);
    handleClose();
}

const [selectedClasses, setSelectedClasses] = useState<string[]>([]);
const [drama, setDrama] = useState<Drama>({
    dramaName: "",
    dramaCountry: "",
    dramaIntro: "",
    dramaYear: "",
    dramaEpisode: ""
});

const queryClient = useQueryClient();
const { data: classes, isLoading } = useClassList();

  // Add inside the AddCar component function
const { mutate } = useMutation(addDrama, {
    onSuccess: () => {queryClient.invalidateQueries(["dramas"]);},
    onError: (err) => {console.error(err);},
});

if (isLoading) return <div>Loading...</div>;

return (
    <>
            <button onClick={handleClickOpen}>New drama</button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>New Drama</DialogTitle>
                <DialogContent>
                    <DramaDialogContent drama={drama} handleChange={handleChange} />
                    <FormControl fullWidth margin="normal">
                        <InputLabel id="class-select-label">Classes</InputLabel>
                        <Select
                            labelId="class-select-label"
                            id="class-select"
                            multiple
                            value={selectedClasses}
                            onChange={(event) => setSelectedClasses(event.target.value as string[])}
                            renderValue={(selected) => (
                                <div>
                                    {(selected as string[]).map((value) => (
                                        <Chip key={value} label={value} />
                                    ))}
                                </div>
                            )}
                        >
                            {classes && classes.map((clazz: any) => (
                                <MenuItem key={clazz.className} value={clazz.className}>
                                    <Checkbox checked={selectedClasses.indexOf(clazz.className) > -1} />
                                    <ListItemText primary={clazz.className} />
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                </DialogContent>
                <DialogActions>
                    <button onClick={handleClose}>Cancel</button>
                    <button onClick={handleSave}>Save</button>
                </DialogActions>
            </Dialog>
        </>
);
}
export default AddDrama;
