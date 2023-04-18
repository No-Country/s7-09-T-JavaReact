import { createSlice } from "@reduxjs/toolkit";
import { InitialAuth } from "../../models/InitialAuth";
import {
  getLocalStorage,
  clearLocalStorage,
  setLocalStorage,
} from "../../utils/LocalStorageFunctions";

export const initialAuth: InitialAuth = {
  token: "",
  user: {
    id: 0,
    name: "",
    lastname: "",
    email: "",
    role: "",
  },
  position: {
    coords: {
      latitude: 0,
      longitude: 0,
    },
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
    setPosition: (state, action) => {
      const auth = getLocalStorage("auth");
      setLocalStorage("auth", { ...auth, position: action.payload });
      state.position = action.payload;
    },
    setLogout: () => {
      clearLocalStorage("auth");
      return initialAuth;
    },
  },
});

export const { setLogin, setPosition, setLogout } = authSlice.actions;

export default authSlice.reducer;
