import Avatar from "../../components/Avatar/Avatar";
import Card from "../../components/Card/Card";
import { AiOutlineCamera } from "react-icons/ai";
import { RiMapPin2Line } from "react-icons/ri";
import { ImPencil } from "react-icons/im";
import { useEffect, useRef, useState } from "react";

enum TypeEdit {
  location = "location",
  email = "email",
  password = "password",
}

const Profile = () => {
  const [location, setLocation] = useState("Córdoba, Argentina");
  const [email, setEmail] = useState("darioelguero@gmail.com");
  const [password, setPassword] = useState("********");
  const [profilePic, setProfilePic] = useState<File>({} as File);
  const [image, setImage] = useState<string>("");

  const [edit, setEdit] = useState({
    location: false,
    email: false,
    password: false,
  });

  const inputLocation = useRef<HTMLInputElement>(null);
  const inputEmail = useRef<HTMLInputElement>(null);
  const inputPass = useRef<HTMLInputElement>(null);

  const handleEditLocation = (type: TypeEdit) => {
    setEdit({ ...edit, [type]: !edit[type] });
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.id === TypeEdit.location) setLocation(event.target.value);
    if (event.target.id === TypeEdit.email) setEmail(event.target.value);
    if (event.target.id === TypeEdit.password) setPassword(event.target.value);
  };

  const uploadImage = () => {
    if (profilePic?.name) {
      const file = URL.createObjectURL(profilePic);
      setImage(file);
    }
  };

  useEffect(() => {
    if (edit.location) inputLocation.current?.focus();
    if (edit.email) inputEmail.current?.focus();
    if (edit.password) inputPass.current?.focus();
  }, [edit]);

  useEffect(() => {
    uploadImage();
  }, [profilePic]);

  return (
    <div className="flex flex-col w-full sm:flex-row sm:gap-8 justify-center">
      <div className="flex flex-col w-full items-center sm:w-[25.5rem] sm:min-w-[20.5rem] sm:my-8 sm:px-6 sm:border sm:moder-[1px] sm:rounded-lg sm:shadow-xl">
        <div className="flex mt-12 relative">
          <Avatar image_url={image} size="xl" />
          <label className="flex justify-center items-center top-0 right-0 bg-white h-12 w-12 rounded-full absolute shadow-xl shadow-black cursor-pointer">
            <AiOutlineCamera size={26} />
            <input
              className="hidden"
              type="file"
              name="profilePic"
              id="file-profile"
              onChange={(e) =>
                setProfilePic(e.target.files ? e.target.files[0] : profilePic)
              }
            />
          </label>
        </div>
        <span className="font-bold mt-5">Dario Elguero</span>
        <div className="w-full p-4">
          <p className="font-bold">Ubicación</p>
          <div className="relative">
            <RiMapPin2Line
              size={22}
              className="text-[#FF5C00] absolute top-2 left-2"
            />
            <div
              className={`${
                edit.location ? "hidden" : ""
              } flex text-xl px-8 items-center rounded-md border-solid border-[0.0625rem] w-full h-10 shadow-md shadow-neutral-400`}
            >
              {location}
            </div>
            <ImPencil
              size={20}
              className="absolute top-2 right-2"
              onClick={() => handleEditLocation(TypeEdit.location)}
            />
            <input
              ref={inputLocation}
              id={TypeEdit.location}
              type="text"
              value={location}
              onChange={handleInputChange}
              className={`${
                !edit.location ? "hidden" : ""
              } text-xl rounded-md border-solid border-[0.0625rem] border-black bg-slate-100 w-full h-10 px-8`}
            />
          </div>

          <p className="font-bold mt-4">Cuenta</p>
          <div className="flex flex-col text-lg pl-2 gap-2 py-2 rounded-md border-solid border-[0.0625rem] w-full h-[7rem] shadow-md shadow-neutral-400">
            <div className="relative flex items-center justify-between">
              <div className="text-[0.9375rem] whitespace-nowrap">E-mail:</div>
              <div
                className={`${
                  edit.email ? "hidden" : ""
                } flex text-[0.9375rem] px-2 items-center rounded-md w-full h-10 justify-end pr-10`}
              >
                {email}
              </div>

              <ImPencil
                size={20}
                className="absolute top-2 right-2"
                onClick={() => handleEditLocation(TypeEdit.email)}
              />
              <input
                ref={inputEmail}
                id={TypeEdit.email}
                type="email"
                value={email}
                onChange={handleInputChange}
                className={`${
                  !edit.email ? "hidden" : ""
                } text-lg text-end rounded-md border-solid border-[0.0625rem] border-black bg-slate-100 w-full h-10 pl-2 pr-10 ml-2`}
              />
            </div>

            <div className="relative flex items-center justify-between">
              <div className="text-[0.9375rem]">Contraseña:</div>
              <div
                className={`${
                  edit.password ? "hidden" : ""
                } flex text-lg px-2 items-center rounded-md w-full h-10 justify-end pr-10`}
              >
                {password}
              </div>

              <ImPencil
                size={20}
                className="absolute top-2 right-2"
                onClick={() => handleEditLocation(TypeEdit.password)}
              />
              <input
                ref={inputPass}
                id={TypeEdit.password}
                type="password"
                value={password}
                onChange={handleInputChange}
                className={`${
                  !edit.password ? "hidden" : ""
                } text-lg text-end rounded-md border-solid border-[0.0625rem] border-black bg-slate-100 w-full h-10 pl-4 pr-10 ml-2`}
              />
            </div>
          </div>
        </div>
      </div>
      <div className="w-full p-4 sm:w-[25.5rem] sm:min-w-[24.5rem] sm:my-8 sm:px-6 sm:border sm:moder-[1px] sm:rounded-lg sm:shadow-xl sm:h-[34rem] sm:overflow-auto">
        <p className="font-bold mt-4">Historial</p>
        <div className="flex flex-col gap-4 md:w-full md:flex-row md:flex-wrap md:justify-center">
          {[...new Array(4).fill(null)].map((card, index) => {
            return (
              <div key={index} className="md:w-[24rem]">
                <Card simple={true} />
              </div>
            );
          })}
        </div>
        <div className="flex w-full justify-end mt-4">
          <span className="text-[#AAAAAA] text-[0.875rem]">Ver más</span>
        </div>
      </div>
    </div>
  );
};
export default Profile;
