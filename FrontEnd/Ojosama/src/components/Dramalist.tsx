
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { DataGrid, GridColDef, GridCellParams } from "@mui/x-data-grid";
import { DramaResponse } from "../types";
import { getDramas, deleteDrama } from "../api/videoapi";
import { Snackbar } from "@mui/material";
import { useState } from "react";
import Button from "@mui/material/Button";
import AddDrama from "./AddDrama";
import EditDrama from "./EditDrama";


function Dramalist() {
const { data, error, isLoading, isError } = useQuery<DramaResponse[]>({
    queryKey: ["dramas"],
    queryFn: getDramas,
});

const queryClient = useQueryClient();

const [open, setOpen] = useState(false);

const { mutate } = useMutation(deleteDrama, {
    onSuccess: () => {
      // success
    setOpen(true);
    queryClient.invalidateQueries({ queryKey: ["dramas"] });
    },
    onError: (err) => {
    console.error(err);
    },
});

const columns: GridColDef[] = [
    { field: "dramaName", headerName: "劇名", width: 200 },
    { field: "dramaCountry", headerName: "國家", width: 200 },
    { field: "dramaIntro", headerName: "簡介", width: 150 },
    { field: "dramaYear", headerName: "年份", width: 150 },
    { field: "dramaEpisode", headerName: "集數", width: 150 },
    {
        field: 'edit',
        headerName: '',
        width: 90,
        sortable: false,
        filterable: false,
        disableColumnMenu: true,
        renderCell: (params: GridCellParams) => 
            <EditDrama dramadata={params.row} />
    },
    {
        field: "delete",
        headerName: "",
        width: 90,
        sortable: false,
        filterable: false,
        disableColumnMenu: true,
        renderCell: (params: GridCellParams) => {
        return (
            <Button
            variant="contained"
            color="error"
            onClick={() => {
            if (
                window.confirm(
                `Are you sure you want to delete ${params.row.dramaName}?`
                )
            ) {
                mutate(params.row._links.drama.href);
            }
            }}
        >
            Delete
            </Button>
        );
    },
    },
];

  if (isLoading) {
    return <span>Loading...</span>;
  }

  if (isError) {
    let errorMessage = "An error occurred";
    if (error instanceof Error) {
      errorMessage = error.message;
    }
    return <span>Error when fetching dramas: {errorMessage}</span>;
  }

  return (
    <>
    <AddDrama />
    <div
        style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
        }}
    >
        <div style={{ height: 600, width: "80%" }}>
        <DataGrid
            rows={data || []}
            columns={columns}
            getRowId={(row) => row._links.self.href}
        />
        <Snackbar
            open={open}
            autoHideDuration={2000}
            onClose={() => setOpen(false)}
            message="Drama deleted"
        />
        </div>
    </div>
    </>
);
}

export default Dramalist;
