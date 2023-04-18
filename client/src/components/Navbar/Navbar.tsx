import { useState, useRef, useEffect } from "react";
import { Link, NavLink } from "react-router-dom";
import { PublicRoutes } from "../../models/routes";
import { IoMenuSharp, IoCloseSharp } from "react-icons/io5";
import TripmateLogo from "../../assets/icons/tripmatelogo.svg";
import LogoutButton from "./LogoutButton";
import { links } from "./Links";
import { useSelector } from "react-redux";
import { AppStore } from "../../app/store";

const Navbar = () => {
  const [onOpen, setOnOpen] = useState(false);
  const ref = useRef<HTMLDivElement>(null);

  const auth = useSelector((store: AppStore) => store.auth);

  const OpenMenu = () => {
    setOnOpen(true);
  };

  const CloseMenu = () => {
    setOnOpen(false);
  };

  useEffect(() => {
    const handleClickOutside = (event: any) => {
      if (!ref?.current?.contains(event.target)) {
        CloseMenu();
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
  }, [ref]);

  return (
    <div className="relative flex flex-grow justify-between p-3 bg-white drop-shadow-md z-50">
      <nav
        id="navbar"
        className="hidden lg:flex flex-grow max-w-6xl mx-auto items-center justify-between"
      >
        <img className="w-28" src={TripmateLogo} alt="Tripmate" />
        <div className="flex gap-6 items-center">
          {links.map((link) => (
            <NavLink
              className="font-normal text-sm py-2 px-3"
              key={link.title}
              to={link.path}
            >
              {link.title}
            </NavLink>
          ))}
          {!auth.token && (
            <div className="flex w-fit gap-3 items-stretch">
              <Link
                to={PublicRoutes.LOGIN}
                className="text-center px-3 py-1 rounded-lg bg-[#FF5C00] text-white font-bold text-base"
              >
                Login
              </Link>
              <Link
                to={PublicRoutes.REGISTER}
                className="text-center px-3 py-1 rounded-lg bg-white border border-[#FF5C00] text-[#FF5C00] font-bold text-base"
              >
                Registro
              </Link>
            </div>
          )}
          <LogoutButton />
        </div>
      </nav>

      <button className="lg:hidden text-2xl text-slate-500" onClick={OpenMenu}>
        <IoMenuSharp />
      </button>
      <img className="w-28 lg:hidden" src={TripmateLogo} alt="Tripmate" />
      <div
        ref={ref}
        className={`lg:hidden absolute top-0 ${
          onOpen ? "left-0" : "-left-96"
        } w-2/4 md:w-2/5 h-screen p-3 bg-white transition-all`}
      >
        <button onClick={CloseMenu} className="text-2xl text-[#FF5C00]">
          <IoCloseSharp />
        </button>
        <nav
          id="navbar-mobile"
          className="h-full flex flex-col px-3 py-6 gap-3"
        >
          {!auth.token && (
            <div className="flex flex-col w-fit gap-3 items-stretch">
              <Link
                to={PublicRoutes.LOGIN}
                onClick={CloseMenu}
                className="text-center py-2 rounded-lg bg-[#FF5C00] text-white font-bold text-base"
              >
                Login
              </Link>
              <Link
                to={PublicRoutes.REGISTER}
                onClick={CloseMenu}
                className="text-center px-3 py-2 rounded-lg bg-white border border-[#FF5C00] text-[#FF5C00] font-bold text-base"
              >
                Registro
              </Link>
            </div>
          )}
          {links.map((link) => (
            <NavLink
              className="font-normal text-sm py-2 px-3"
              key={link.title}
              to={link.path}
              onClick={CloseMenu}
            >
              {link.title}
            </NavLink>
          ))}
          <LogoutButton />
          <div className="flex items-end pb-6 w-full h-full">
            <img className="w-20" src={TripmateLogo} alt="Tripmate" />
          </div>
        </nav>
      </div>
    </div>
  );
};
export default Navbar;
