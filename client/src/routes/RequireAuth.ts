import { Navigate, useLocation } from "react-router-dom";
import { PublicRoutes } from "../models/routes";
import { useSelector } from "react-redux";
import { AppStore } from "../app/store";

interface Props {
  children: React.ReactElement;
}

export const RequireAuth = ({ children }: Props) => {
  const location = useLocation();
  const userToken = useSelector((store: AppStore) => store.auth.token);
  if (!userToken) {
    return Navigate({
      to: `/${PublicRoutes.LOGIN}`,
      state: { path: location.pathname },
      replace: true,
    });
  }
  return children;
};
