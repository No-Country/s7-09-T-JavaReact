import { Link } from "react-router-dom";
import Spinner from "../Spinner/Spinner";
import Card from "../Card/Card";
import { Experiences as ExperiencesModel } from "../../models/Experiences";

type Props = {
  isLoading: boolean
  isFetching: boolean
  experiencesData: ExperiencesModel
}

const Experiences = ({experiencesData, isLoading, isFetching}: Props) => {

  return (
    <div className="w-full flex flex-col md:flex-wrap md:flex-row gap-6 items-center justify-center lg:justify-evenly xl:justify-between">
      <>
        {isLoading || isFetching ? (
          <Spinner />
        ) : (
          <>
            {experiencesData?.length > 0
              ? experiencesData?.map((experience) => (
                  <Link key={experience.id} to={`/experience/${experience.id}`}>
                    <Card
                      id={experience.id}
                      averageScore={experience.averageScore}
                      description={experience.description}
                      title={experience.title}
                      subtitle={experience.subtitle}
                      images={experience.images}
                    />
                  </Link>
                ))
              : null}
          </>
        )}
      </>
    </div>
  );
};
export default Experiences;
