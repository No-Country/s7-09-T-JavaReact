import ScoreStar from "../ScoreStar/ScoreStar";
import { useRef } from "react";
import { Swiper, SwiperRef, SwiperSlide } from "swiper/react";
import { Navigation, Pagination, Mousewheel, Keyboard } from "swiper";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import { Experience } from "../../models/Experiences";

interface Iprops {
  simple?: boolean;
}

type Props = Iprops & Experience;

const Card = ({
  simple,
  title,
  averageScore,
  subtitle,
  images,
  price,
}: Props) => {
  const ref = useRef<SwiperRef>(null);

  return (
    <div
      className={`${
        simple ? "flex" : "w-[20.5rem] h-[26.4375rem]"
      } border-solid border bg-white rounded-xl shadow-xl`}
    >
      <div
        className={`${
          simple ? "w-[8.375rem] h-[8.75rem]" : "w-[20.5rem] h-[17.625rem]"
        }`}
      >
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
          {images.length > 0 ? (
            images.map((image, index) => {
              return (
                <SwiperSlide
                  key={image.id}
                  className={`${simple ? "h-[8.75rem]" : "h-[282px]"}`}
                >
                  <img
                    className={`${
                      simple ? "rounded-l-xl" : "rounded-t-xl"
                    } h-full object-cover object-center mx-auto`}
                    src={image.url}
                    alt={image.alt}
                  />
                </SwiperSlide>
              );
            })
          ) : (
            <SwiperSlide>
              <img
                className="img__product"
                src={
                  "https://previews.123rf.com/images/incomible/incomible1710/incomible171000558/87904767-paisaje-de-verano-con-el-icono-de-%C3%A1rboles.jpg"
                }
                alt="default "
              />
            </SwiperSlide>
          )}
        </Swiper>
      </div>
      <div
        className={`${
          simple ? "px-4 py-4" : "px-6 py-4"
        } flex flex-col gap-0.5`}
      >
        <div className=" flex gap-4 justify-start items-start">
          <h2 className="font-medium text-lg">{title}</h2>
          <ScoreStar scoreStar={averageScore} type="simple" />
        </div>
        <p className="text-[0.9375rem]">{subtitle}</p>
        <p className="text-[0.9375rem]">{price ? `$${price}` : "Gratis"}</p>
      </div>
    </div>
  );
};
export default Card;
