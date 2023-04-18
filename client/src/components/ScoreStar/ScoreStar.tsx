import { Dispatch, FC, SetStateAction } from "react";
import { AiFillStar, AiOutlineStar } from "react-icons/ai";

interface Iprops {
  scoreStar: number;
  type: "simple" | "complete";
  setScore?: Dispatch<SetStateAction<number>>;
}

const ScoreStar: FC<Iprops> = ({ scoreStar, type, setScore }) => {
  return (
    <div className="flex font-bold text-lg text-[#FF5C00] items-center">
      {type === "complete" && (
        <div className="flex mr-2">
          {[...new Array(5)].map((score, index) => {
            return index < scoreStar ? (
              <AiFillStar
                key={`start-${index}`}
                className="star__icon"
                color="#FF5C00"
                size={20}
                onClick={() => setScore && setScore(index + 1)}
              />
            ) : (
              <AiOutlineStar
                key={`start-${index}`}
                className="star__icon"
                color="#FF5C00"
                size={20}
                onClick={() => setScore && setScore(index + 1)}
              />
            );
          })}
        </div>
      )}
      <p className="flex">{scoreStar?.toFixed(1)}</p>
      {type === "simple" && (
        <AiFillStar className="star__icon" color="#FF5C00" size={20} />
      )}
    </div>
  );
};

export default ScoreStar;
