"use client";

import {
  Checkbox,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { setIdArr } from "../redux/reducers/idArrSlice";
import type { RootState } from "../redux/store";
import { setInvoiceData } from "../redux/reducers/invoiceDataSlice";

export default function MainTable({ result }: any) {
  const invoiceData = useSelector(
    (state: RootState) => state.invoiceDataStore.invoiceData
  );
  const modalValue = useSelector(
    (state: RootState) => state.modalStore.modalValue
  );
  const addEditModal = useSelector(
    (state: RootState) => state.modalStore.addEditModal
  );
  const deleteModal = useSelector(
    (state: RootState) => state.modalStore.deleteModal
  );
  const correspondenceModal = useSelector(
    (state: RootState) => state.modalStore.correspondenceModal
  );
  const idArr = useSelector((state: RootState) => state.idArrStore.idArr);
  const dispatch = useDispatch();

  // const [invoiceData, setInvoiceData] = useState(result);
  const [allChecked, setAllChecked] = useState(false);

  const toCheckeAll = (): void => {
    if (allChecked) {
      dispatch(setIdArr([]));
    } else {
      let arr = document.getElementsByClassName("PrivateSwitchBase-input");
      let tempArr = [];

      for (let i = 1; i < arr.length; i++) {
        tempArr.push(Number(arr[i].id));
        (arr[i] as HTMLInputElement).checked = true;
      }
      dispatch(setIdArr(tempArr));
    }
    setAllChecked(!allChecked);
  };

  const isAllChecked = (): Array<number> => {
    let arr = document.getElementsByClassName("PrivateSwitchBase-input");
    let tempArr = [];

    for (let i = 1; i < arr.length; i++) {
      if ((arr[i] as HTMLInputElement).checked) {
        tempArr.push(Number(arr[i].id));
      }
    }
    dispatch(setIdArr(tempArr));
    return tempArr;
  };

  useEffect(() => {
    console.log(1, "hello");

    if (modalValue === "reload")
      fetch(
        "http://localhost:8080/invoice-management-system/read-invoice?pageCount=0"
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
  }, [dispatch, modalValue]);

  useEffect(() => {
    console.log(4, "hello");

    if (modalValue == "edit" && !addEditModal) {
      console.log(2, "hello");
      dispatch(setIdArr([]));
    } else if (modalValue == "delete" && !deleteModal) {
      console.log(3, "hello");
      dispatch(setIdArr([]));
    } else if (modalValue == "correspondence" && !correspondenceModal) {
      console.log(3, "hello");
      dispatch(setIdArr([]));
    }
    //   fetch(
    //     "http://localhost:8080/invoice-management-system/read-invoice?pageCount=0"
    //   )
    //     .then((res) => res.json())
    //     .then((result) => {
    //       console.log(result);
    //       dispatch(setInvoiceData(result));
    //       // setLoading(false);
    //       // console.log(data);
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
  }, [dispatch, addEditModal, deleteModal, modalValue, correspondenceModal]);
  return (
    <div>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow className=" h-[20px]">
            <TableCell sx={{ padding: "0px 0px 0px 16px", border: "0px" }}>
              <Checkbox
                sx={{ color: "whitesmoke", border: "0px" }}
                onClick={toCheckeAll}
                checked={idArr.length === 10 ? true : false}
              />
            </TableCell>
            <TableCell
              sx={{
                padding: "0px 0px 0px 16px",
                color: "whitesmoke",
                border: "0px",
              }}
            >
              Customer Name
            </TableCell>
            <TableCell
              sx={{
                padding: "0px 0px 0px 16px",
                color: "whitesmoke",
                border: "0px",
              }}
            >
              Customer No.
            </TableCell>
            <TableCell
              sx={{
                padding: "0px 0px 0px 16px",
                color: "whitesmoke",
                border: "0px",
              }}
            >
              Invoice No.
            </TableCell>
            <TableCell
              sx={{
                padding: "0px 0px 0px 16px",
                color: "whitesmoke",
                border: "0px",
              }}
              align="right"
            >
              Invoice Amount
            </TableCell>
            <TableCell
              sx={{
                padding: "0px 0px 0px 16px",
                color: "whitesmoke",
                border: "0px",
              }}
              align="right"
            >
              Due Date
            </TableCell>
            <TableCell
              sx={{
                padding: "0px 0px 0px 16px",
                color: "whitesmoke",
                border: "0px",
              }}
            >
              Predicted Payment Date
            </TableCell>
            <TableCell
              sx={{
                padding: "0px 0px 0px 16px",
                color: "whitesmoke",
                border: "0px",
              }}
            >
              Predicted Aging Bucket
            </TableCell>
            <TableCell
              sx={{
                padding: "0px 0px 0px 16px",
                color: "whitesmoke",
                border: "0px",
              }}
            >
              Notes
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {invoiceData &&
            invoiceData.map((row: any) => (
              <TableRow
                // className=" h-[20px]"
                key={row.docId}
                sx={{
                  height: "20px",
                  "&nth-of-type(even)": { backgroundColor: "#000000" },
                }}
              >
                <TableCell
                  sx={{ padding: "0px 0px 0px 16px", border: "0px" }}
                  scope="row"
                >
                  <Checkbox
                    checked={idArr.includes(row.docId) ? true : false}
                    // checked={allCheck}
                    id={row.docId}
                    className="rowCheckBox"
                    onClick={isAllChecked}
                    sx={{ color: "whitesmoke", border: "0px" }}
                  />
                </TableCell>
                <TableCell
                  id={`customerName-${row.docId}`}
                  sx={{
                    padding: "0px 0px 0px 16px",
                    color: "whitesmoke",
                    border: "0px",
                  }}
                >
                  {row.nameCustomer}
                </TableCell>
                <TableCell
                  id={`customerNo-${row.docId}`}
                  sx={{
                    padding: "0px 0px 0px 16px",
                    color: "whitesmoke",
                    border: "0px",
                  }}
                >
                  {row.custNumber}
                </TableCell>
                <TableCell
                  id={`invoiceNo-${row.docId}`}
                  sx={{
                    padding: "0px 0px 0px 16px",
                    color: "whitesmoke",
                    border: "0px",
                  }}
                >
                  {row.docId}
                </TableCell>
                <TableCell
                  id={`invoiceAmount-${row.docId}`}
                  sx={{
                    padding: "0px 0px 0px 16px",
                    color: "whitesmoke",
                    border: "0px",
                  }}
                  align="right"
                >
                  {row.totalOpenAmount}K
                </TableCell>
                <TableCell
                  id={`dueDate-${row.docId}`}
                  sx={{
                    padding: "0px 0px 0px 16px",
                    color: "whitesmoke",
                    border: "0px",
                  }}
                  align="right"
                >
                  {row.dueInDate}
                </TableCell>
                <TableCell
                  id={`predictedPaymentDate-${row.docId}`}
                  sx={{
                    padding: "0px 0px 0px 16px",
                    color: "whitesmoke",
                    border: "0px",
                  }}
                  align="center"
                >
                  --
                </TableCell>
                <TableCell
                  id={`predictedAgingBucket${row.docId}`}
                  sx={{
                    padding: "0px 0px 0px 16px",
                    color: "whitesmoke",
                    border: "0px",
                  }}
                  align="center"
                >
                  --
                </TableCell>
                <TableCell
                  id={`notes-${row.docId}`}
                  sx={{
                    padding: "0px 20px 0px 16px",
                    color: "whitesmoke",
                    border: "0px",
                  }}
                >
                  {row.notes}
                </TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </div>
  );
}
