import { useQuery } from "react-query";
import { getRequest } from "../services/httpRequest";
import { Experience, Experiences } from "../models/Experiences";


const getExperiences = () => getRequest("/api/experiences");
const getExperiencesById = (id: string) => getRequest(`/api/experiences/${id}`);
const getExperiencesByTitle = (title: string) => getRequest(`/api/experiences/title?title=${title}`);

export const useGetExperiences = (onSuccess: (data: Experiences) => void) => {
    return useQuery(["experiences"], () => getExperiences(), {
      onSuccess: onSuccess,
      onError: (error: any) => {
        throw new Error(error.message);
      },
      refetchOnWindowFocus: true
    });
  };

export const useGetExperiencesById = (id: string) => {
    return useQuery(["experiencesById", id], () => getExperiencesById(id), {
      onSuccess: (data: Experience) => data,
      onError: (error: any) => {
        throw new Error(error.message);
      },
      refetchOnWindowFocus: true
    });
  };


export const useGetExperiencesByTitle = (title: string, onSuccess: (data: Experiences) => void, onError: (data: Experiences) => void) => {
    return useQuery(["experiencesByTitle", title], () => getExperiencesByTitle(title), {
      onSuccess: onSuccess,
      onError: onError,
      refetchOnWindowFocus: true,
      enabled: false
    });
  };