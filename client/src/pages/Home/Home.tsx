import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import Slider from "../../components/Slider/Slider";
import Spinner from "../../components/Spinner/Spinner";
import { AppStore } from "../../app/store";
import Experiences from "../../components/Experiences/Experiences";
import {
  useGetExperiences,
  useGetExperiencesByLocation,
  useGetExperiencesByTitle,
} from "../../hooks/useExperiences";
import { Experiences as ExperiencesModel } from "../../models/Experiences";
import { IoMdSearch } from "react-icons/io";
import { RiMapPin2Line } from "react-icons/ri";
import LinkCard from "./components/Card/Card";
import entertainment from "../../assets/icons/entertainment.svg";
import adventure from "../../assets/icons/adventure.svg";
import education from "../../assets/icons/education.svg";
import family from "../../assets/icons/family.svg";
import food from "../../assets/icons/food.svg";
import tourism from "../../assets/icons/tourism.svg";
import { setPosition } from "../../app/state/authSlice";

const categories = [
  {
    name: "Restaurantes",
    icon: food,
    id: 1
  },
  {
    name: "Entretenimiento",
    icon: entertainment,
    id: 2
  },
  {
    name: "Aventura",
    icon: adventure,
    id: 6
  },
  {
    name: "Educación",
    icon: education,
    id: 4
  },
  {
    name: "Turismo",
    icon: tourism,
    id: 5
  },
  {
    name: "Familiares",
    icon: family,
    id: 3
  },
];

const Home = () => {
  const dispatch = useDispatch();
  const token = useSelector((store: AppStore) => store.auth.token);
  const {latitude, longitude} =useSelector((store: AppStore) => store.auth.position.coords)

  const [error, setError] = useState<string | null>(null);


  const onSuccess = (data: ExperiencesModel) => {
    setExperiences(data);
  };
  const [experiences, setExperiences] = useState<ExperiencesModel>(
    {} as ExperiencesModel
  );
  const { isLoading, isFetching, refetch } = useGetExperiences(onSuccess);
  const [title, setTitle] = useState("");

  const handleOnChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(event.target.value);
  };

  const onSuccessLocation = (locationResult: ExperiencesModel) => {
    setExperiences(locationResult);
  };

  const onErrorLocation = (data: ExperiencesModel) => {
    setExperiences(data);
  };

  const onSuccessSearch = (searchResult: ExperiencesModel) => {
    setExperiences(searchResult);
  };

  const onErrorSearch = (data: ExperiencesModel) => {
    setExperiences(data);
  };

  const { refetch: refetchLocation, isFetching: isFetchingLocation, isLoading: isLoadingLocation } = useGetExperiencesByLocation(latitude, longitude, onSuccessLocation, onErrorLocation);
  const { refetch: refetchSearch } = useGetExperiencesByTitle(title, onSuccessSearch, onErrorSearch);

  const onSearch = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    refetchSearch();
  };

  useEffect(() => {
    if (token)
      if (window.navigator.geolocation) {
        window.navigator.geolocation.getCurrentPosition(
          (position) => {
            dispatch(
              setPosition({
                coords: {
                  latitude: position.coords.latitude,
                  longitude: position.coords.longitude,
                },
              })
            );
          },
          (error) => {
            setError(error.message);
          },
          {
            enableHighAccuracy: true,
          }
        );
      } else {
        setError("Geolocation is not supported by this browser.");
      }
  }, []);

  useEffect(() => {
    if (token && latitude !== 0) refetchLocation()
    if (!token) refetch()
  }, [token]);

  return (
    <div className="w-full flex flex-col justify-between pb-6">
      <div className="w-full max-w-6xl flex flex-col md:mx-auto gap-3">
        <div className="w-full h-72 md:h-[25rem] flex items-center justify-center text-3xl text-white font-bold relative">
          <div className="w-full h-full flex flex-col gap-6 items-center lg:items-start justify-end  mb-24 absolute z-[3] px-3 md:px-6">
            <h2 className="text-center lg:w-3/5 lg:text-start font-bold text-2xl lg:text-5xl px-16 lg:p-0">
              Disfrutar de una Experiencia ahora está a tu alcance
            </h2>
            <form onSubmit={onSearch} className="w-full lg:w-2/5 self-start">
              <div className="relative">
                <div className="absolute inset-y-0 left-0 flex items-center pl-1 hover:cursor-pointer">
                  <i className="h-full flex items-center justify-center px-1 hover:cursor-pointer">
                    <RiMapPin2Line className="text-[#FF5C00] text-2xl" />
                  </i>
                </div>
                <input
                  onChange={handleOnChange}
                  type="search"
                  id="default-search"
                  className="block w-full p-2 pl-9 text-sm text-gray-500 font placeholder:text-gray-500 placeholder:font-normal focus:outline-none border border-gray-300 rounded-lg bg-slate-100"
                  placeholder="Buscar experiencias"
                  minLength={4}
                />
                <button
                  type="submit"
                  className="absolute right-0 bottom-0 bg-[#FF5C00] hover:bg-[#FF5C00] focus:outline-none font-medium rounded-r-lg text-sm h-full px-2"
                >
                  <IoMdSearch className="text-2xl" />
                </button>
              </div>
            </form>
          </div>
          <Slider />
        </div>
        <div className="w-full flex flex-col gap-3 xl:px-0">
          <div className="flex w-full gap-x-3 lg:gap-x-6 items-center lg:justify-center md:mx-auto pl-6 md:px-3 xl:px-0 pb-3 overflow-x-scroll md:overflow-x-hidden font-medium">
            {categories
              ? categories.map((category) => (
                  <LinkCard
                    key={category.name}
                    name={category.name}
                    param={category.name}
                    icon={category.icon}
                  />
                ))
              : null}
          </div>
          {isLoading || isFetching || isFetchingLocation || isLoadingLocation ? (
            <Spinner />
          ) : (
            <Experiences
              isLoading={isLoading}
              isFetching={isFetching}
              experiencesData={experiences}
            />
          )}
        </div>
      </div>
    </div>
  );
};
export default Home;
