import { useRef } from "react";
import { Swiper, SwiperRef, SwiperSlide } from "swiper/react";
import { Navigation, Pagination, Mousewheel, Keyboard } from "swiper";
import ScoreStar from "../../components/ScoreStar/ScoreStar";
import Avatar from "../../components/Avatar/Avatar";
import Reviews from "../../components/Reviews/Reviews";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import { useParams } from "react-router-dom";
import { useGetExperiencesById } from "../../hooks/useExperiences";

const ExperienceDetail = () => {
  const ref = useRef<SwiperRef>(null);

  const {id} = useParams()

  const {data: experiences, isSuccess} = useGetExperiencesById(id!)

  return (
    <div className="flex flex-col w-full sm:flex-row-reverse sm:justify-center sm:gap-10 sm:pt-10 sm:pb-10">
      <div className="w-full h-[17.625rem] sm:w-[35rem] sm:h-[35rem] sm:gap-6">
        <Swiper
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
                <SwiperSlide
                  key={image.id}
                  className="h-[17.625rem] sm:w-full sm:h-[30rem]"
                >
                  <img
                    className="h-full sm:rounded-lg sm:object-cover object-cover object-center mx-auto"
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
        <div className="hidden mt-6 sm:flex sm:w-[35rem] sm:h-[30rem] sm:bg-green-300 sm:rounded-lg"></div>
      </div>
      <div className="flex flex-col gap-0.5 px-4 py-4 sm:w-[25.5rem] border-solid border bg-white shadow-xl rounded-lg">
        <div className=" flex gap-4 justify-start items-start">
          <Avatar size="xs" image_url={null} />
          <h2 className="font-bold text-lg leading-5">
            <p>{experiences?.title}</p>
            <p>{experiences?.city?.city}, {experiences?.city?.country}</p>
          </h2>
        </div>
        <p className="text-[0.9375rem] mt-2">
          {experiences?.subtitle}
        </p>
        <div className="flex justify-between">
          <ScoreStar scoreStar={experiences?.averageScore!} type="simple" />
          <p className="text-[1.2rem]">$500.000</p>
        </div>
        <div className="flex w-full items-center my-4">
          <div className="flex h-0.5 w-full bg-gray-300"></div>
        </div>
        <p className="text-[15px] leading-5 my-4">
          {experiences?.description}
        </p>
        <div className="flex w-full items-center my-4">
          <div className="flex h-0.5 w-full bg-gray-300"></div>
        </div>

        <div className="flex flex-col leading-5">
          <span>Dirección: Lorem Ipsum</span>
          <span>Horas: 09:00 a 22:00</span>
          <span>Teléfono: 012-54556-6849</span>
          <span>Citas: whatsapp.com</span>
        </div>

        <div className="flex w-full items-center my-4">
          <div className="flex h-0.5 w-full bg-gray-300"></div>
        </div>
        <div className="sm:h-[30rem] sm:overflow-y-auto sm:px-4">
          <Reviews />
        </div>
      </div>
    </div>
  );
};
export default ExperienceDetail;
