import { Paper } from "@mui/material";
import React from "react";
import Navbar from "./Navbar";
import MainTable from "./MainTable";

export default function Main() {
  return (
    <div>
      <Paper
        sx={{
          backgroundColor: "#2D424E",
          margin: "20px",
        }}
        elevation={1}
      >
        <Navbar />
        <MainTable />
      </Paper>
    </div>
  );
}
