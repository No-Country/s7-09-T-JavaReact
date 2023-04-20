import { useMutation, useQuery, useQueryClient } from "react-query";
import {
  deleteRequest,
  getRequest,
  postRequest,
  putRequest,
} from "../services/httpRequest";
import { Success, Error } from "../utils/notification";
import { ReviewsList } from "../models/ReviewsList";
import { ReviewPost } from "../models/ReviewPost";

const getListReviews = (experienceId: number) =>
  getRequest(`/api/experiences/${experienceId}/reviews`);

const createReview = (review: ReviewPost) => {
  const dataReview = { score: review.score, review: review.review };
  return postRequest(
    dataReview,
    `/api/experiences/${review.experienceId}/reviews`
  );
};

const updateReview = (review: ReviewPost) => {
  const dataReview = { score: review.score, review: review.review };
  return putRequest(
    `/api/experiences/${review.experienceId}/reviews`,
    dataReview
  );
};

const deleteReview = (experienceId: number) => {
  return deleteRequest(`/api/experiences/${experienceId}/reviews`);
};

export const useGetListReviews = (id: number) => {
  return useQuery(["listReviews", id], () => getListReviews(id), {
    onSuccess: (data: ReviewsList) => data,
    onError: (error: any) => {
      Error(error.message);
    },
  });
};

export const useDeleteReview = () => {
  const queryClient = useQueryClient();
  return useMutation(deleteReview, {
    onSuccess: (res) => {
      Success("El comentario", "se elimino correctamente");
      queryClient.invalidateQueries(["listReviews"]);
      queryClient.invalidateQueries(["experiencesById"]);
    },
    onError: (error) => {
      Error("Error al eliminar el comentario");
      console.log(error);
    },
  });
};

export const useCreateReview = () => {
  const queryClient = useQueryClient();
  return useMutation(createReview, {
    onSuccess: (res) => {
      Success("El comentario", "se cargo correctamente");
      queryClient.invalidateQueries(["listReviews"]);
      queryClient.invalidateQueries(["experiencesById"]);
    },
    onError: (error) => {
      Error("Error al crear el comentario");
      console.log(error);
    },
  });
};

export const useUpdateReview = () => {
  const queryClient = useQueryClient();
  return useMutation(updateReview, {
    onSuccess: (res) => {
      Success("Se actualizo", "correctamente");
      queryClient.invalidateQueries(["listReviews"]);
      queryClient.invalidateQueries(["experiencesById"]);
    },
    onError: (error) => {
      Error("Error al actualizar el comentario");
      console.log(error);
    },
  });
};
