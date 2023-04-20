import { useQuery, useQueryClient } from "react-query";
import { getRequest } from "../services/httpRequest";
import { Experience, Experiences } from "../models/Experiences";

const getExperiences = () => getRequest("/api/experiences");
const getExperiencesById = (id: string) => getRequest(`/api/experiences/${id}`);
const getExperiencesByTitle = (title: string) =>
  getRequest(`/api/experiences/title?title=${title}`);
const getExperiencesByLocation = (latitude: number, longitude: number) =>
  getRequest(
    `/api/experiences/find?latitude=${latitude}&longitude=${longitude}&distance=5`
  );
const getExperiencesByCategory = (
  latitude: number,
  longitude: number,
  categoryId: number
) =>
  getRequest(
    `/api/experiences/find/category?latitude=${latitude}&longitude=${longitude}&distance=5&categoryId=${categoryId}`
  );

export const useGetExperiences = (onSuccess: (data: Experiences) => void) => {
  return useQuery(["experiences"], () => getExperiences(), {
    onSuccess: onSuccess,
    onError: (error: any) => {
      throw new Error(error.message);
    },
    refetchOnWindowFocus: false,
    enabled: false,
  });
};

export const useGetExperiencesById = (id: string) => {
  return useQuery(["experiencesById", id], () => getExperiencesById(id), {
    onSuccess: (data: Experience) => data,
    onError: (error: any) => {
      throw new Error(error.message);
    },
    refetchOnWindowFocus: true,
  });
};

export const useGetExperiencesByTitle = (
  title: string,
  onSuccess: (data: Experiences) => void,
  onError: (data: Experiences) => void
) => {
  return useQuery(
    ["experiencesByTitle", title],
    () => getExperiencesByTitle(title),
    {
      onSuccess: onSuccess,
      onError: onError,
      refetchOnWindowFocus: true,
      enabled: false,
    }
  );
};

export const useGetExperiencesByLocation = (
  latitude: number,
  longitude: number,
  onSuccess: (data: Experiences) => void,
  onError: (data: Experiences) => void
) => {
  return useQuery(
    ["experiencesByLocation"],
    () => getExperiencesByLocation(latitude, longitude),
    {
      onSuccess: onSuccess,
      onError: onError,
      refetchOnWindowFocus: false,
      enabled: !!latitude || !!longitude,
    }
  );
};

export const useGetExperiencesByCategory = (
  latitude: number,
  longitude: number,
  categoryId: number,
  onSuccess: (data: Experiences) => void,
  onError: (data: Experiences) => void
) => {
  return useQuery(
    ["experiencesByCategory"],
    () => getExperiencesByCategory(latitude, longitude, categoryId),
    {
      onSuccess: onSuccess, //(data: Experiences) => data,
      onError: onError,
      refetchOnWindowFocus: false,
      enabled: !!latitude || !!longitude,
    }
  );
};

export const useGetExperienceCategoryData = (key: string) => {
  const queryClient = useQueryClient();

  return queryClient.getQueryData(key) as Experiences;
};
