import Avatar from "../../components/Avatar/Avatar";
import Card from "../../components/Card/Card";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { AiOutlineCamera } from "react-icons/ai";
import { RiMapPin2Line } from "react-icons/ri";
import { AppStore } from "../../app/store";
import { Experiences } from "../../models/Experiences";

const Profile = () => {
  const user = useSelector((store: AppStore) => store.auth.user);

  const [location, setLocation] = useState("Córdoba, Argentina");
  const [experiencesList, setExperiencesList] = useState<Experiences>([]);

  const [profilePic, setProfilePic] = useState<File>({} as File);
  const [image, setImage] = useState<string>("");

  const name = `${user.name} ${user.lastname}`;
  const existExperience = experiencesList.length > 0;

  const uploadImage = () => {
    if (profilePic?.name) {
      const file = URL.createObjectURL(profilePic);
      setImage(file);
    }
  };

  useEffect(() => {
    uploadImage();
  }, [profilePic]);

  return (
    <div className="flex flex-col w-full min-w-[18rem] sm:flex-row sm:gap-8 justify-center">
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
        <span className="font-bold mt-5">{name}</span>
        <div className="w-full p-4">
          <p className="font-bold">Ubicación</p>
          <div className="relative">
            <RiMapPin2Line
              size={22}
              className="text-[#FF5C00] absolute top-2 left-2"
            />
            <div
              className={`flex text-xl px-8 items-center rounded-md border-solid border-[0.0625rem] w-full h-10 shadow-md shadow-neutral-400`}
            >
              {location}
            </div>
          </div>
          <p className="font-bold mt-4">Cuenta</p>
          <div className="flex flex-col text-lg px-3 gap-2 py-2 rounded-md border-solid border-[0.0625rem] w-full h-[7rem] shadow-md shadow-neutral-400">
            <div className="relative flex items-center justify-between">
              <div className="text-[0.9375rem] whitespace-nowrap">E-mail:</div>
              <div
                className={`flex text-[0.9375rem] items-center rounded-md w-full h-10 justify-end`}
              >
                {user.email}
              </div>
            </div>
            <div className="relative flex items-center justify-between">
              <div className="text-[0.9375rem]">Rol:</div>
              <div
                className={`flex text-lg items-center rounded-md w-full h-10 justify-end`}
              >
                {user.role}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="w-full p-4 sm:w-[25.5rem] sm:min-w-[24.5rem] sm:my-8 sm:px-6 sm:border sm:moder-[1px] sm:rounded-lg sm:shadow-xl sm:h-[34rem] sm:overflow-auto">
        <p className="font-bold mt-4">Historial</p>
        <div className="flex flex-col gap-4 md:w-full md:flex-row md:flex-wrap md:justify-center">
          {experiencesList?.map((card, index) => {
            return (
              <div key={index} className="md:w-[24rem]">
                <Card
                  simple={true}
                  id={card.id}
                  title={card.title}
                  images={card.images}
                  averageScore={card.averageScore}
                  description={card.description}
                />
              </div>
            );
          })}
        </div>
        <div
          className={` ${
            existExperience ? "justify-end" : "justify-center"
          } flex w-full  mt-4`}
        >
          <span className="text-[#AAAAAA] text-[0.875rem]">
            {existExperience ? "Ver más" : "Sin Experiencias"}
          </span>
        </div>
      </div>
    </div>
  );
};
export default Profile;
