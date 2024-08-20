import { Button, Paper, makeStyles } from "@mui/material";
import Header from "./components/Header";
import Main from "./components/Main";
import AddEditModal from "./components/AddEditModal";
import DeleteModal from "./components/DeleteModal";
import Correspondence from "./components/Correspondence";

export default async function Home() {
  return (
    <>
      <Header />
      <Main />
      <AddEditModal />
      <DeleteModal />
      <Correspondence />
    </>
  );
}
