import { useDispatch } from "react-redux";
import { setLogout } from "../../app/state/authSlice";

import LogoutLogo from "../../assets/icons/logout.svg";
import { useNavigate } from "react-router-dom";
import { PublicRoutes } from "../../models/routes";

const LogoutButton = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleClick = () => {
    dispatch(setLogout());
    navigate(`/${PublicRoutes.HOME}`, { replace: true });
  };

  return (
    <button
      onClick={handleClick}
      className="flex gap-1 items-center font-normal text-sm"
    >
      <img src={LogoutLogo} alt="Logout" />
      Desconectarse
    </button>
  );
};
export default LogoutButton;
