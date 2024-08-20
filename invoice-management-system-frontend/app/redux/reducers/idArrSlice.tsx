import { PayloadAction, createSlice } from "@reduxjs/toolkit";

export interface IdArrState {
  idArr: number[];
}

const initialState: IdArrState = {
  idArr: [],
};

export const idArrSlice = createSlice({
  name: "idArrStore",
  initialState,
  reducers: {
    setIdArr: (state, action: PayloadAction<number[]>) => {
      state.idArr = action.payload;
    },
  },
});

export const { setIdArr } = idArrSlice.actions;
export default idArrSlice.reducer;
