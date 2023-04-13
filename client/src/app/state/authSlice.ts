import { createSlice } from "@reduxjs/toolkit";
import { InitialAuth } from "../../models/InitialAuth";
import {
  getLocalStorage,
  clearLocalStorage,
} from "../../utils/LocalStorageFunctions";

export const initialAuth: InitialAuth = {
  token: "",
  user: {
    id: 0,
    name: "",
    lastName: "",
    email: "",
    role: "",
  },
};

export const authSlice = createSlice({
  name: "auth",
  initialState: getLocalStorage("auth") ? getLocalStorage("auth") : initialAuth,

  reducers: {
    setLogin: (state, action) => {
      state.user = action.payload.user;
      state.token = action.payload.token;
    },
    setLogout: () => {
      clearLocalStorage("auth");
      return initialAuth;
    },
  },
});

export const { setLogin, setLogout } = authSlice.actions;

export default authSlice.reducer;
