"use client";

import { Button, InputAdornment, TextField } from "@mui/material";
import AddSharpIcon from "@mui/icons-material/AddSharp";
import EditSharpIcon from "@mui/icons-material/EditSharp";
import RemoveSharpIcon from "@mui/icons-material/RemoveSharp";
import SearchIcon from "@mui/icons-material/Search";
import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  setModalValue,
  openAddEditModal,
  openDeleteModal,
  openCorrespondenceModal,
} from "../redux/reducers/modalSlice";
import type { RootState } from "../redux/store";
import { setInvoiceData } from "../redux/reducers/invoiceDataSlice";

export default function Navbar() {
  const idArr = useSelector((state: RootState) => state.idArrStore.idArr);
  const addEditModal = useSelector(
    (state: RootState) => state.modalStore.addEditModal
  );
  const deleteModal = useSelector(
    (state: RootState) => state.modalStore.deleteModal
  );
  const dispatch = useDispatch();

  const handleSearchChange = (value: string) => {
    fetch(
      `http://localhost:8080/invoice-management-system/search-invoice?id=${value}`
    )
      .then((res) => res.json())
      .then((result) => {
        console.log(result);
        dispatch(setInvoiceData(result));
        // setLoading(false);
        // console.log(data);
      })
      .catch((err) => {
        console.log(err);
      });
    dispatch(setModalValue("reload"));
  };

  return (
    <div>
      <nav>
        <Button
          sx={{ margin: "20px 4px 20px 20px", border: "2px solid gray" }}
          variant="outlined"
        >
          Predict
        </Button>
        <Button
          sx={{ margin: "20px 4px 20px 20px" }}
          variant="outlined"
          disabled={idArr.length === 1 ? false : true}
          onClick={() => {
            dispatch(openCorrespondenceModal(true));
            dispatch(setModalValue("correspondence"));
          }}
        >
          View Correspondence
        </Button>
        <Button
          disabled={idArr.length === 0 ? false : true}
          sx={{ margin: "20px 4px 20px 20px" }}
          variant="outlined"
          onClick={() => {
            dispatch(openAddEditModal(true)), dispatch(setModalValue("add"));
          }}
          startIcon={<AddSharpIcon />}
        >
          Add
        </Button>
        <Button
          disabled={idArr.length === 1 ? false : true}
          sx={{ margin: "20px 4px 20px 20px" }}
          variant="outlined"
          onClick={() => {
            dispatch(openAddEditModal(true)), dispatch(setModalValue("edit"));
          }}
          startIcon={<EditSharpIcon />}
        >
          Edit
        </Button>
        <Button
          disabled={idArr.length === 0 ? true : false}
          sx={{ margin: "20px 4px 20px 20px" }}
          // className="m-5"
          variant="outlined"
          onClick={() => {
            dispatch(openDeleteModal(true)), dispatch(setModalValue("delete"));
          }}
          startIcon={<RemoveSharpIcon />}
        >
          Delete
        </Button>
        <TextField
          onChange={(e) => handleSearchChange(e.target.value)}
          size="small"
          sx={{
            margin: "20px 4px 20px 20px",
            // border: "2px solid gray",
            // borderRadius: "4px",
            input: { color: "white" },
          }}
          id="outlined-basic"
          variant="outlined"
          placeholder="Search by Invoice Number"
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <SearchIcon />
              </InputAdornment>
            ),
          }}
        />
      </nav>
    </div>
  );
}
