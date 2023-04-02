import { ChangeEvent, useState } from "react";
import ScoreStar from "../ScoreStar/ScoreStar";
import Avatar from "../Avatar/Avatar";

type Review = {
  id: number;
  username: string;
  avatar: string;
  date: string;
  score: number;
  review: string;
};

const listReviews: Array<Review> = [
  {
    id: 1,
    username: "Gabriela Merlo",
    avatar: "",
    date: "Hace 1 día",
    score: 3,
    review: "Estuvo bien, pero para mi gusto le falto algo de emoción",
  },
  {
    id: 2,
    username: "Roberto Martínez",
    avatar: "",
    date: "Hace 2 día",
    score: 5,
    review: "Lo pase expectacular, es excelente para relajarse",
  },
  {
    id: 3,
    username: "Joaquín García",
    avatar: "",
    date: "Hace 5 día",
    score: 2,
    review:
      "Creo que ese dia el guia estaba de mal humor porque fue muy aburrido todo",
  },
  {
    id: 4,
    username: "Ana Medina",
    avatar: "",
    date: "Hace 9 día",
    score: 4,
    review:
      "Muy lindo, hermosos paisajes y sumamente especial para estar alejado de todo ruido",
  },
];

const Reviews = () => {
  const [score, setScore] = useState<number>(1);
  const [reviewList, setReviewList] = useState<Array<Review>>(listReviews);
  const [comment, setComment] = useState<string>("");

  const handleInputChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
    setComment(event.target.value);
  };

  const handleClickSend = () => {
    const newReview = {
      id: 5,
      date: "hace un ratito",
      avatar: "",
      review: comment,
      score: score,
      username: "Dario Elguero",
    };

    setReviewList([newReview, ...reviewList]);
    setComment("");
  };
  return (
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
          value={"Enviar"}
          onClick={handleClickSend}
          className="rounded-md bg-[#FF5C00] text-white font-medium w-20 h-10 mt-2"
        />
      </div>
      {reviewList.map((review) => {
        return (
          <div>
            <div className="flex w-full items-center my-4">
              <div className="flex h-0.5 w-full bg-gray-300"></div>
            </div>
            <div className="flex flex-col">
              <div className="flex justify-between">
                <div className="flex items-center gap-3">
                  <Avatar size="sm" image_url={""} />
                  <div className="flex flex-col items-start">
                    <span className="font-bold">{review.username}</span>
                    <ScoreStar type="simple" scoreStar={review.score} />
                  </div>
                </div>
                <span className="text-[#a9a9a9]">{review.date}</span>
              </div>
              <p className="mt-4 leading-5">{review.review}</p>
            </div>
          </div>
        );
      })}
    </div>
  );
};
export default Reviews;
