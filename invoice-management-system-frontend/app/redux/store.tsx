import { configureStore } from "@reduxjs/toolkit";
import modalSlice from "./reducers/modalSlice";
import idArrSlice from "./reducers/idArrSlice";
import invoiceDataSlice from "./reducers/invoiceDataSlice";

export const store = configureStore({
  reducer: {
    modalStore: modalSlice,
    idArrStore: idArrSlice,
    invoiceDataStore: invoiceDataSlice,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
