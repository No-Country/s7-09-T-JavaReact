import { FC } from "react";
import { AiFillStar, AiOutlineStar } from "react-icons/ai";

interface Iprops {
  scoreStar: number;
  type: "simple" | "complete";
}

const ScoreStar: FC<Iprops> = ({ scoreStar, type }) => {
  return (
    <div className="flex font-bold text-lg text-[#FF5C00] items-center">
      <div>
        {type === "complete" &&
          [...new Array(5)].map((score, index) => {
            return index < scoreStar ? (
              <AiFillStar
                key={`start-${index}`}
                className="star__icon"
                color="#FF5C00"
                size={20}
              />
            ) : (
              <AiOutlineStar
                key={`start-${index}`}
                className="star__icon"
                color="#FF5C00"
                size={20}
              />
            );
          })}
      </div>
      <p className="flex">{scoreStar.toFixed(1)}</p>
      {type === "simple" && (
        <AiFillStar className="star__icon" color="#FF5C00" size={20} />
      )}
    </div>
  );
};

export default ScoreStar;
