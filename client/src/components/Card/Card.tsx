import { ScoreStar } from "../ScoreStar/ScoreStar";
import { useRef } from "react";
import { Swiper, SwiperRef, SwiperSlide } from "swiper/react";
import { Navigation, Pagination, Mousewheel, Keyboard } from "swiper";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";

const listImage = [
  "https://www.clarin.com/img/2015/03/21/H1GaoLq6Xl_1256x620.jpg",
  "https://www.veroniatours.com/imagenes/jardingeneralife.jpg",
  "https://previews.123rf.com/images/bloodua/bloodua1701/bloodua170103247/72051455-jardines-y-fuentes-en-el-palacio-de-la-alhambra-en-granada-en-un-hermoso-d%C3%ADa-de-verano-espa%C3%B1a.jpg",
];

const Card = () => {
  const ref = useRef<SwiperRef>(null);
  
  return (
    <div className="w-[20.5rem] h-[26.4375rem] border-solid border bg-white rounded-xl shadow-xl">
      <div className="w-[20.5rem] h-[17.625rem]">
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
          {listImage.length > 0 ? (
            listImage.map((image, index) => {
              return (
                <SwiperSlide key={`prod-${index}`} className="h-[282px]">
                  <img
                    className="h-full rounded-t-xl"
                    src={image}
                    alt={`experience-${index}`}
                  />
                </SwiperSlide>
              );
            })
          ) : (
            <SwiperSlide >
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
      <div className="flex flex-col gap-0.5 px-6 py-4">
        <div className=" flex gap-4 justify-start items-start">
          <h2 className="font-medium text-lg leading-5">
            Biaka Biodescanso, Cali, Colombia
          </h2>
          <ScoreStar scoreStar={3} type="simple" />
        </div>
        <p className="text-[0.9375rem]">
          Glamping en el bosque de los farallones de Cali
        </p>
        <p className="text-[0.9375rem]">$500.000</p>
      </div>
    </div>
  );
};
export default Card;
