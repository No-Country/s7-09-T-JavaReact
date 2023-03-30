import { FC } from "react";
import { AiFillStar, AiOutlineStar } from "react-icons/ai";

interface Iprops {
  scoreStar: number;
  type: "simple" | "complete";
}

export const ScoreStar: FC<Iprops> = ({ scoreStar, type }) => {
  return (
    <div className="flex font-medium text-lg text-[#FF5C00] items-center">
      <div>
        {type === "complete" &&
          [...new Array(5)].map((score, index) => {
            return index < scoreStar ? (
              <AiFillStar
                key={`start-${index}`}
                className="star__icon"
                color="#FF5C00"
              />
            ) : (
              <AiOutlineStar
                key={`start-${index}`}
                className="star__icon"
                color="#FF5C00"
              />
            );
          })}
      </div>
      <p className="flex">{scoreStar}</p>
      {type === "simple" && (
        <AiFillStar className="star__icon" color="#FF5C00" />
      )}
    </div>
  );
};
