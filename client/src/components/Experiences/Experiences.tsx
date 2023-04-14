import { Link } from "react-router-dom";
import { useGetExperiences } from "../../hooks/useExperiences";
import Spinner from "../Spinner/Spinner";
import Card from "../Card/Card";

const Experiences = () => {
  const { data: experiences, isLoading } = useGetExperiences();

  return (
    <div className="w-full flex flex-col md:flex-wrap md:flex-row gap-6 items-center justify-center lg:justify-evenly xl:justify-between">
      <>
        {isLoading ? (
          <Spinner />
        ) : (
          <>
            {experiences!.length > 0
              ? experiences?.map((experience) => (
                  <Link key={experience.id} to={`/experience/${experience.id}`}>
                    <Card
                      id={experience.id}
                      averageScore={experience.averageScore}
                      description={experience.description}
                      title={experience.title}
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
