import { Navigate, useLocation } from 'react-router-dom'
import { PublicRoutes } from '../models/routes';

interface Props {
    children: React.ReactElement;
  }

  export const RequireAuth = ({ children }: Props) => {
      const location = useLocation()
      const logged = false
  if (!logged) {
    return Navigate({to: `/${PublicRoutes.LOGIN}`, state: {path: location.pathname}, replace: true})
  }
  return children
}