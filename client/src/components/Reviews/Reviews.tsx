import { ChangeEvent, useEffect, useState } from "react";
import { useSelector } from "react-redux";
import ScoreStar from "../ScoreStar/ScoreStar";
import Avatar from "../Avatar/Avatar";
import { AppStore } from "../../app/store";
import {
  useCreateReview,
  useDeleteReview,
  useGetListReviews,
  useUpdateReview,
} from "../../hooks/useReviews";
import Swal from "sweetalert2";
import { ReviewPost } from "../../models/ReviewPost";
import { Error } from "../../utils/notification";
import { MdDeleteForever } from "react-icons/md";
import { BsPencilFill } from "react-icons/bs";

interface Props {
  experienceId: number;
}

const Reviews = ({ experienceId }: Props) => {
  const auth = useSelector((store: AppStore) => store.auth);

  const { data: reviews, isSuccess } = useGetListReviews(experienceId);
  const { mutate: createReviews, isSuccess: reviewSuccess } = useCreateReview();
  const { mutate: deleteReviews } = useDeleteReview();
  const { mutate: updateReviews } = useUpdateReview();

  const [score, setScore] = useState<number>(1);
  const [comment, setComment] = useState<string>("");
  const [editComment, setEditComment] = useState<boolean>(true);
  const [edit, setEdit] = useState<boolean>(false);
  const [isHovering, setIsHovering] = useState<boolean>(false);

  const handleMouseEnter = () => {
    setIsHovering(true);
  };

  const handleMouseLeave = () => {
    setIsHovering(false);
  };

  const isNotCommented = () => {
    if (auth.token) {
      const commented = reviews?.reviews.content.find(
        (review) => review.profile.id === auth.user.id
      );
      if (commented) return setEditComment(false);
      return setEditComment(true);
    }
    setEditComment(false);
  };

  const handleInputChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
    setComment(event.target.value);
  };

  const handleClickSend = () => {
    if (!comment) return Error("El comentario no puede estar vacio");
    if (comment.length < 10)
      return Error("El comentario debe ser mayor a 10 caracteres");

    const newReview = {
      experienceId: experienceId,
      score: score,
      review: comment,
    } as ReviewPost;

    if (edit) {
      updateReviews(newReview);
    } else {
      createReviews(newReview);
    }

    setComment("");
    setEditComment(false);
  };

  const deleteReview = () => {
    Swal.fire({
      title: "Seguro que desea Eliminar?",
      text: "Esta accion no se podrá revertir",
      icon: "question",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, eliminar",
    }).then((result) => {
      if (result.isConfirmed) {
        deleteReviews(experienceId);
        setEditComment(true);
      }
    });
  };

  const updateReview = (review: string) => {
    setEditComment(true);
    setComment(review);
    setEdit(true);
  };

  useEffect(() => {
    isNotCommented();
  }, [reviews]);

  return (
    <div>
      {editComment && (
        <div>
          <ScoreStar scoreStar={score} type="complete" setScore={setScore} />
          <label htmlFor="coment" className="mt-4 font-semibold">
            Dejar comentario
          </label>
          <textarea
            id="coment"
            className="text-xl rounded-md border-solid border-[0.0625rem] border-black w-full h-24 p-4"
            value={comment}
            onChange={handleInputChange}
          />
          <div className="flex justify-end">
            <input
              type="submit"
              value={edit ? "Actualizar" : "Enviar"}
              onClick={handleClickSend}
              className="rounded-md bg-[#FF5C00] text-white font-medium w-20 h-10 mt-2"
            />
          </div>
        </div>
      )}
      {reviews?.reviews.content.map((review) => {
        return (
          <div
            key={review.id}
            onMouseEnter={
              review.profile.id === auth.user.id ? handleMouseEnter : undefined
            }
            onMouseLeave={
              review.profile.id === auth.user.id ? handleMouseLeave : undefined
            }
            className="relative"
          >
            <div className="flex w-full items-center my-4">
              <div className="flex h-0.5 w-full bg-gray-300"></div>
            </div>
            <div className="flex flex-col">
              <div className="flex justify-between">
                <div className="flex items-center gap-3">
                  <Avatar size="sm" image_url={""} />
                  <div className="flex flex-col items-start">
                    <span className="font-bold">{`${review.profile.name} ${review.profile.lastname}`}</span>
                    <ScoreStar type="simple" scoreStar={review.score} />
                  </div>
                </div>
                <span className="text-[#a9a9a9]">{review.date.toString()}</span>
              </div>
              <p className="mt-4 leading-5">{review.review}</p>
              {review.profile.id === auth.user.id
                ? isHovering && (
                    <div
                      onClick={handleMouseLeave}
                      className="absolute right-0 bg-white cursor-pointer p-1 border-solid border shadow-xl rounded-lg"
                    >
                      <div className="flex gap-2">
                        <BsPencilFill
                          color="#FFCD00"
                          size={20}
                          onClick={() => updateReview(review.review)}
                        />
                        <MdDeleteForever
                          color="#FF0000"
                          size={22}
                          onClick={deleteReview}
                        />
                      </div>
                    </div>
                  )
                : null}
            </div>
          </div>
        );
      })}
    </div>
  );
};
export default Reviews;
