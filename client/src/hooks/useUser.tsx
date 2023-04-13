import { useMutation } from "react-query";
import { useNavigate } from "react-router-dom";
import { PublicRoutes } from "../models/routes";
import { postRequest } from "../services/httpRequest";
import { Success, Error } from "../utils/notification";
import { useDispatch } from "react-redux";
import { setLogin } from "../app/state/authSlice";
import { setLocalStorage } from "../utils/LocalStorageFunctions";

const createUser = (user: {}) => postRequest(user, "/api/users");
const loginUser = (user: {}) => postRequest(user, "/api/auth/login");

export const useCreateUser = () => {
  const navigate = useNavigate();

  return useMutation(createUser, {
    onSuccess: (res) => {
      Success("¡Felicitaciones!", "Tu cuenta ha sido creada con éxito.");
      navigate(`/${PublicRoutes.LOGIN}`, { replace: true });
    },
    onError: (error) => {
      Error("Error al crear la cuenta");
      console.log(error);
    },
  });
};

export const useLogin = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  return useMutation(loginUser, {
    onSuccess: (auth) => {
      const authInStorage = { token: auth.jwt, user: auth.user };
      setLocalStorage("auth", authInStorage);

      dispatch(setLogin(authInStorage));

      Success(
        `¡Hola ${auth.user.name}!`,
        "¡Qué bueno tenerte de nuevo en Tripmate!"
      );

      navigate(`/${PublicRoutes.HOME}`, { replace: true });
    },
    onError: () => {
      Error("Error al iniciar sesión");
    },
  });
};
