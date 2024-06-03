import DialogContent from '@mui/material/DialogContent';


type DialogFormProps = {
    drama: Drama;
    handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

function DramaDialogContent({ drama, handleChange}: DialogFormProps) {
    return (
    <DialogContent>
    <input
    placeholder="Name"
    name="dramaName"
    value={drama.dramaName}
    onChange={handleChange}
  />
  <br />
  <input
    placeholder="Country"
    name="dramaCountry"
    value={drama.dramaCountry}
    onChange={handleChange}
  />
  <br />
  <input
    placeholder="Intro"
    name="dramaIntro"
    value={drama.dramaIntro}
    onChange={handleChange}
  />
  <br />
  <input
    placeholder="Year"
    name="dramaYear"
    value={drama.dramaYear}
    onChange={handleChange}
  />
  <br />
  <input
    placeholder="Episode"
    name="dramaEpisode"
    value={drama.dramaEpisode}
    onChange={handleChange}
  />
    </DialogContent>
    );
}

export default DramaDialogContent;
