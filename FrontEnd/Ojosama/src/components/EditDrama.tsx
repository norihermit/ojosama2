import React, { useState } from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import DramaDialogContent from './DramaDialogContent';
import { updateDrama } from '../api/videoapi';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { Drama, DramaResponse } from '../types';

type FormProps = {
    dramadata: DramaResponse;
}

type DramaEntry = {
    drama: Drama;
    url: string;
}

function EditDrama({ dramadata }: FormProps) {
    const [drama, setDrama] = useState<Drama>({
        dramaName: '',
        dramaCountry: '',
        dramaIntro: '',
        dramaYear: 0,
        dramaEpisode: 0
    });

    const [open, setOpen] = useState(false);

    const queryClient = useQueryClient();
    const { mutate } = useMutation(updateDrama, {
        onSuccess: () => {
            queryClient.invalidateQueries(['dramas']);
        },
        onError: (err) => {
            console.error(err);
        }
    });

    const handleClickOpen = () => {
        setDrama({
            dramaName: dramadata.dramaName,
            dramaCountry: dramadata.dramaCountry,
            dramaIntro: dramadata.dramaIntro,
            dramaYear: dramadata.dramaYear,
            dramaEpisode: dramadata.dramaEpisode
        });
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleSave = () => {
        const url = dramadata._links.self.href;
        const dramaEntry: DramaEntry = { drama, url };
        mutate(dramaEntry);
        setDrama({
            dramaName: '',
            dramaCountry: '',
            dramaIntro: '',
            dramaYear: 0,
            dramaEpisode: 0
        });
        setOpen(false);
    };

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setDrama({ ...drama, [event.target.name]: event.target.value });
    };

    return (
        <>
            <Button variant="contained" color="primary" onClick={handleClickOpen}>
                Edit
            </Button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Edit Drama</DialogTitle>
                <DramaDialogContent drama={drama} handleChange={handleChange} />
                <DialogActions>
                    <Button onClick={handleClose} color="secondary">
                        Cancel
                    </Button>
                    <Button onClick={handleSave} color="primary">
                        Save
                    </Button>
                </DialogActions>
            </Dialog>
        </>
    );
}

export default EditDrama;
