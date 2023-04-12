import { useMutation } from "react-query";
import { useNavigate } from "react-router-dom";
import { PublicRoutes } from "../models/routes";
import { postRequest } from "../services/httpRequest";
import { Success, Error } from "../utils/notification";

const createUser = (user: {}) => postRequest(user, "/api/users");
const login = (user: {}) => postRequest(user, "/api/auth/login");

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

export const useLogin = (
  onSuccess: (data: {}) => void,
  onError: (error: {}) => void
) => {
  return useMutation(["login"], login, {
    onSuccess,
    onError,
  });
};
