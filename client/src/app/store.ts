import { configureStore } from "@reduxjs/toolkit";
import { InitialAuth } from "../models/InitialAuth";
import auth from "./state/authSlice";

export interface AppStore {
  auth: InitialAuth;
}

export type AppDispatch = typeof store.dispatch;

export const store = configureStore({
  reducer: {
    auth,
  }
});
