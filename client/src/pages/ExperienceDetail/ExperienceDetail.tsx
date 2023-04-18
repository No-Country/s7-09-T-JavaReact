import { useRef } from "react";
import { useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import { AppStore } from "../../app/store";
import Reviews from "../../components/Reviews/Reviews";
import Description from "../../components/ExperienceDescription/ExperienceDescription";
import LocationMap from "../../components/LocationMap/LocationMap";
import { useGetExperiencesById } from "../../hooks/useExperiences";
import { Swiper, SwiperRef, SwiperSlide } from "swiper/react";
import { Navigation, Pagination, Mousewheel, Keyboard } from "swiper";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";

const ExperienceDetail = () => {
  const position = useSelector((store: AppStore) => store.auth.position);

  const ref = useRef<SwiperRef>(null);
  const { id } = useParams();
  const { data: experiences, isSuccess } = useGetExperiencesById(id!);

  return (
    <div className="w-full md:h-[100svh] md:p-6 md:my-6 max-w-6xl mx-auto grid grid-cols-1 auto-rows-auto md:grid-cols-7 md:grid-rows-2 md:mx-auto gap-6">
      <div className="h-full md:col-start-4 md:col-end-8 md:self-start rounded-xl">
        <Swiper
          className="w-full max-h-[250px] md:h-full md:max-h-full"
          ref={ref}
          cssMode={true}
          navigation={{
            prevEl: ".swiper-button-prev",
            nextEl: ".swiper-button-next",
          }}
          slidesPerView={1}
          mousewheel={true}
          keyboard={true}
          modules={[Navigation, Pagination, Mousewheel, Keyboard]}
        >
          <div className="swiper-button-container">
            <div className="swiper-button-prev text-white"></div>
            <div className="swiper-button-next text-white"></div>
          </div>
          {isSuccess ? (
            experiences.images?.map((image, index) => {
              return (
                <SwiperSlide key={image.id}>
                  <img
                    className="w-full h-full sm:rounded-lg sm:object-cover object-center mx-auto"
                    src={image.url}
                    alt={image.alt}
                  />
                </SwiperSlide>
              );
            })
          ) : (
            <SwiperSlide>
              <img
                className="img__product rounded-xl"
                src={
                  "https://previews.123rf.com/images/incomible/incomible1710/incomible171000558/87904767-paisaje-de-verano-con-el-icono-de-%C3%A1rboles.jpg"
                }
                alt="default "
              />
            </SwiperSlide>
          )}
        </Swiper>
      </div>
      <div className="p-3 md:hidden">
        <Description
          averageScore={experiences?.averageScore!}
          title={experiences?.title!}
          city={experiences?.city}
          subtitle={experiences?.subtitle}
          description={experiences?.description}
          id={experiences?.id!}
          images={experiences?.images!}
        />
      </div>
      <div className="w-full h-full min-h-[300px] p-3 md:p-0 md:col-start-4 md:col-end-8 md:row-start-2 md:self-start sm:rounded-xl">
          <LocationMap
            latitude={experiences?.latitude ? experiences.latitude : 0}
            longitude={experiences?.longitude ? experiences.longitude : 0}
            title={experiences?.title!}
            yourLocation={position}
          />
      </div>
      <div className="min-h-full overflow-y-scroll md:hidden p-3">
        <Reviews />
      </div>

      <div className="hidden h-full md:col-start-1 md:col-end-4 md:row-start-1 md:row-end-3 md:self-start md:flex flex-col gap-0.5 p-6 border-solid border bg-white shadow-xl rounded-lg md:overflow-y-auto">
        <Description
          averageScore={experiences?.averageScore!}
          title={experiences?.title!}
          city={experiences?.city}
          subtitle={experiences?.subtitle}
          description={experiences?.description}
          id={experiences?.id!}
          images={experiences?.images!}
        />
        <div className="w-full pr-3 overflow-y-scroll">
          <Reviews />
        </div>
      </div>
    </div>
  );
};
export default ExperienceDetail;
