import { useState, MouseEvent, ChangeEvent } from "react";
import { Link } from "react-router-dom";
import { PublicRoutes } from "../../models/routes";
import { useCreateUser } from "../../hooks/useUser";
import { FcGoogle } from "react-icons/fc";
import { FaFacebookF } from "react-icons/fa";
import { AiOutlineEyeInvisible, AiOutlineEye } from "react-icons/ai";
import { BiLoaderCircle } from "react-icons/bi";

type RegisterData = {
  name: string;
  lastname: string;
  userName: string;
  email: string;
  password: string;
  confirmPass?: string;
};
const PersonalForm = () => {
  enum inputPass {
    text = "text",
    password = "password",
  }

  const [userToRegister, setUserToRegister] = useState({} as RegisterData);
  const { mutate: addUser, isLoading } = useCreateUser();
  const [isVisiblePass, setIsVisiblePass] = useState<inputPass>(
    inputPass.password
  );

  const controlFrom = (): boolean => {
    if (
      !userToRegister.name ||
      !userToRegister.lastname ||
      !userToRegister.email ||
      userToRegister.password === ""
    )
      return true;

    if (userToRegister.password !== userToRegister.confirmPass) return true;

    return false;
  };

  const handleChangeInput = (event: ChangeEvent<HTMLInputElement>) => {
    setUserToRegister({
      ...userToRegister,
      [event.target?.name]: event.target?.value,
    });
  };

  const handleSubmit = (event: MouseEvent<HTMLInputElement>) => {
    event.preventDefault();
    addUser(userToRegister);
  };

  return (
    <div className="flex justify-center w-full">
      <div className="flex flex-col items-center w-full sm:w-[25.5rem] sm:mt-10 sm:p-6 sm:border sm:moder-[1px] sm:rounded-l-lg mb-10">
        <form action="" className="flex flex-col p-4 w-full">
          <p className="font-bold mb-4">Crea tu cuenta personal</p>
          <div className="flex flex-1 gap-6 justify-center">
            <div>
              <label htmlFor="name" className="mt-4 font-semibold">
                Nombre
              </label>
              <input
                id="name"
                name="name"
                type="text"
                required
                onChange={handleChangeInput}
                className="text-xl rounded-md border-solid border-[0.0625rem] border-black w-full h-10 p-4"
              />
            </div>
            <div>
              <label htmlFor="lastname" className="mt-4 font-semibold">
                Apellido
              </label>
              <input
                id="lastname"
                name="lastname"
                type="text"
                required
                onChange={handleChangeInput}
                className="text-xl rounded-md border-solid border-[0.0625rem] border-black w-full h-10 p-4"
              />
            </div>
          </div>
          <label htmlFor="email" className="mt-4 font-semibold">
            E-mail
          </label>
          <input
            id="email"
            name="email"
            type="text"
            required
            onChange={handleChangeInput}
            className="text-xl rounded-md border-solid border-[0.0625rem] border-black w-full h-10 p-4"
          />
          <label htmlFor="password" className="mt-4 font-semibold">
            Contraseña
          </label>
          <div className="relative">
            <input
              id="password"
              name="password"
              type={isVisiblePass}
              required
              onChange={handleChangeInput}
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
          <label htmlFor="confirmPass" className="mt-4 font-semibold">
            Confirma contraseña
          </label>
          <div className="relative">
            <input
              id="confirmPass"
              name="confirmPass"
              type={isVisiblePass}
              required
              onChange={handleChangeInput}
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
          {!isLoading ? (
            <input
              type="submit"
              value={"Registrar"}
              onClick={handleSubmit}
              disabled={controlFrom()}
              className="rounded-md bg-[#FF5C00] text-white font-medium w-full h-10 mt-4 disabled:bg-gray-400"
            />
          ) : (
            <div className="flex justify-center items-center rounded-md bg-[#ca9a7e] text-white font-medium w-full h-10 mt-4">
              <BiLoaderCircle size={34} className="animate-spin" />
            </div>
          )}
        </form>
        <div className="flex w-full items-center px-4 my-4">
          <div className="flex h-0.5 w-full bg-gray-300"></div>
          <span className="px-4 text-gray-800 font-bold text-sm whitespace-nowrap">
            O inicia con
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
          <span>¿Ya tienes cuenta?</span>
          <Link to={`/${PublicRoutes.LOGIN}`}>
            <span className="text-[#FF5C00] font-bold">Inicia Sesión</span>
          </Link>
        </div>
        <div className="flex flex-col items-center my-2">
          <span>Al crear cuenta aceptas nuestros</span>
          <span className="text-[#FF5C00] font-bold">
            Términos y Condiciones
          </span>
        </div>
      </div>
      <div className="max-md:hidden border w-[25.5rem] mt-10 rounded-r-lg shadow-lg mb-10 shadow-dark-500">
        <img
          src="../../../public/images/register_image.avif"
          alt="register_image"
          className="object-fill h-full"
        />
      </div>
    </div>
  );
};
export default PersonalForm;
