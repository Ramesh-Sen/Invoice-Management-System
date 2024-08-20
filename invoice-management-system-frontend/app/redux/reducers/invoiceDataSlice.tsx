import { PayloadAction, createSlice } from "@reduxjs/toolkit";

export interface InvoiceData {
  invoiceData: [];
}

const initialState: InvoiceData = {
  invoiceData: [],
};

export const invoiceDataSlice = createSlice({
  name: "invoiceDataStore",
  initialState,
  reducers: {
    setInvoiceData: (state, action: PayloadAction<[]>) => {
      // fetch(
      //   "http://localhost:8080/invoice-management-system/read-invoice?pageCount=0"
      // )
      //   .then((res) => res.json())
      //   .then((result) => {
      state.invoiceData = action.payload;
      // })
      // .catch((err) => {
      //   console.log(err);
      // });
    },
  },
});

export const { setInvoiceData } = invoiceDataSlice.actions;
export default invoiceDataSlice.reducer;
