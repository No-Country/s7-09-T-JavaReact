import logo from "../../assets/icons/tripmatelogo.svg";
import { useState, MouseEvent } from "react";
import { Link } from "react-router-dom";
import { PublicRoutes } from "../../models/routes";
import { FcGoogle } from "react-icons/fc";
import { FaFacebookF } from "react-icons/fa";
import { AiOutlineEyeInvisible, AiOutlineEye } from "react-icons/ai";

const Login = () => {
  enum inputPass {
    text = "text",
    password = "password",
  }

  const [isVisiblePass, setIsVisiblePass] = useState<inputPass>(
    inputPass.password
  );

  const handleSubmit = (event: MouseEvent<HTMLInputElement>) => {
    event.preventDefault();
  };

  return (
    <div className="flex flex-col items-center w-full">
      <img className="w-[18.5rem] my-12" src={logo} alt="tripmate" />
      <form action="" className="flex flex-col p-4 w-full">
        <p className="font-bold">Iniciar sesión</p>
        <label htmlFor="email" className="mt-4 font-semibold">
          E-mail
        </label>
        <input
          id="email"
          type="text"
          className="text-xl rounded-md border-solid border-[0.0625rem] border-black w-full h-10 p-4"
        />
        <label htmlFor="password" className="mt-4 font-semibold">
          Contraseña
        </label>
        <div className="relative">
          <input
            id="password"
            type={isVisiblePass}
            className="text-xl rounded-md border-solid border-[0.0625rem] border-black w-full h-10 p-4"
          />
          {isVisiblePass === inputPass.password ? (
            <AiOutlineEye
              size={25}
              className="absolute top-2 right-4 text-xl cursor-pointer"
              onClick={() => setIsVisiblePass(inputPass.text)}
            />
          ) : (
            <AiOutlineEyeInvisible
              size={25}
              className="absolute top-2 right-4 text-xl cursor-pointer"
              onClick={() => setIsVisiblePass(inputPass.password)}
            />
          )}
        </div>
        <input
          type="submit"
          value={"Iniciar sesión"}
          onClick={handleSubmit}
          className="rounded-md bg-[#FF5C00] text-white font-medium w-full h-10 mt-4"
        />
        <span className="text-right font-semibold mt-2">
          Olvidé mi contraseña
        </span>
      </form>
      <div className="flex w-full items-center px-4 my-4">
        <div className="flex h-0.5 w-full bg-gray-300"></div>
        <span className="px-4 text-gray-800 font-bold text-sm whitespace-nowrap">
          Iniciar con
        </span>
        <div className="flex h-0.5 w-full bg-gray-300"></div>
      </div>
      <div className="flex gap-4">
        <div className="flex items-center justify-center rounded-full shadow-xl w-10 h-10 border cursor-pointer">
          <FcGoogle size={28} />
        </div>
        <div className="flex items-center justify-center rounded-full shadow-xl w-10 h-10 border cursor-pointer">
          <FaFacebookF size={24} color="#4267B2" />
        </div>
      </div>
      <div className="flex gap-2 mt-2">
        <span>¿No tienes cuenta?</span>
        <Link to={`/${PublicRoutes.REGISTER}`}>
          <span className="text-[#FF5C00] font-bold">Registrate</span>
        </Link>
      </div>
    </div>
  );
};
export default Login;
