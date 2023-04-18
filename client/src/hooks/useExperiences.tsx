import { useQuery } from "react-query";
import { getRequest } from "../services/httpRequest";
import { Experience, Experiences } from "../models/Experiences";


const getExperiences = () => getRequest("/api/experiences");
const getExperiencesById = (id: string) => getRequest(`/api/experiences/${id}`);

export const useGetExperiences = () => {
    return useQuery(["experiences"], () => getExperiences(), {
      onSuccess: (data: Experiences) => data,
      onError: (error: any) => {
        throw new Error(error.message);
      },
      refetchOnWindowFocus: true
    });
  };

export const useGetExperiencesById = (id: string) => {
    return useQuery(["experiencesById"], () => getExperiencesById(id), {
      onSuccess: (data: Experience) => data,
      onError: (error: any) => {
        throw new Error(error.message);
      },
      refetchOnWindowFocus: true
    });
  };