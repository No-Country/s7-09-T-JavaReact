import { getRequest, postRequest, putRequest } from "../../services/httpRequest";
import { createSlice, Dispatch } from "@reduxjs/toolkit";
import { InitialAuth } from "../../models/InitialAuth";
import {
  setLocalStorage,
  getLocalStorage,
  clearLocalStorage
} from "../../utils/LocalStorageFunctions";
import { LoginData } from "../../models/LoginData";

export const initialAuth: InitialAuth = {
  token: "",
  user: {
    id: 0,
    name: "",
    lastName: "",
    email: "",
    role: "",
  }
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
    
    
    
    
  }
});

export const {
  setLogin,
  setLogout,
} = authSlice.actions;

export default authSlice.reducer;

export const loginUser = (dataLogin: LoginData) => async (dispatch: Dispatch) => {
  try {
    const auth = (await postRequest(dataLogin, "/users/login")) as InitialAuth;
    if (auth.token !== "") {
      dispatch(setLogin(auth));
      const authInStorage = { token: auth.token, user: auth.user };
      setLocalStorage("auth", authInStorage);
      return { login: true, msg: "Usuario logeado con éxito!" };
    }
    return false;
  } catch (error) {
    const msgError = error as string;
    return { login: false, msg: msgError.toString() };
  }
};

// export const updateUserPassword = (dataUser: ChangePasswords) => async () => {
//   try {
//     const request = await putRequest(`/users/`, `${dataUser.id}/password`, dataUser);
//     return "Cambio de contraseña exitoso";
//   } catch (error) {
//     const msgError = error as string;
//     return msgError.toString();
//   }
// };
