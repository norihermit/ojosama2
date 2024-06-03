import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogTitle from "@mui/material/DialogTitle";
import { useState } from "react";
import { useQueryClient, useMutation } from "@tanstack/react-query";
import { addDrama } from '../api/videoapi';
import DramaDialogContent from "./DramaDialogContent";

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
    mutate(drama);
    setDrama({ dramaName: '', dramaCountry: '', dramaIntro:'', dramaYear: 0 ,dramaEpisode:0});
    handleClose();
}


const [drama, setDrama] = useState<Drama>({
    dramaName: "",
    dramaCountry: "",
    dramaIntro: "",
    dramaYear: "",
    dramaEpisode: ""
});

const queryClient = useQueryClient();

  // Add inside the AddCar component function
const { mutate } = useMutation(addDrama, {
    onSuccess: () => {queryClient.invalidateQueries(["dramas"]);},
    onError: (err) => {console.error(err);},
});

return (
    <>
    <button onClick={handleClickOpen}>New drama</button>
    <Dialog open={open} onClose={handleClose}>
        <DialogTitle>New Drama</DialogTitle>
        <DramaDialogContent drama={drama} handleChange={handleChange}/>
        <DialogActions>
            <button onClick={handleClose}>Cancel</button>
            <button onClick={handleSave}>Save</button>
        </DialogActions>
    </Dialog>
    </>
);
}
export default AddDrama;
