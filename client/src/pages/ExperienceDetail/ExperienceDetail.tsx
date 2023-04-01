import { useRef } from "react";
import { Swiper, SwiperRef, SwiperSlide } from "swiper/react";
import { Navigation, Pagination, Mousewheel, Keyboard } from "swiper";
import ScoreStar from "../../components/ScoreStar/ScoreStar";
import Avatar from "../../components/Avatar/Avatar";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";

const listImage: Array<string> = [
  "https://www.clarin.com/img/2015/03/21/H1GaoLq6Xl_1256x620.jpg",
  "https://www.veroniatours.com/imagenes/jardingeneralife.jpg",
  "https://previews.123rf.com/images/bloodua/bloodua1701/bloodua170103247/72051455-jardines-y-fuentes-en-el-palacio-de-la-alhambra-en-granada-en-un-hermoso-d%C3%ADa-de-verano-espa%C3%B1a.jpg",
];

const ExperienceDetail = () => {
  const ref = useRef<SwiperRef>(null);

  return (
    <div className="w-full border-solid border bg-white shadow-xl">
      <div className="w-full h-[17.625rem]">
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
            listImage?.map((image, index) => {
              return (
                <SwiperSlide key={`prod-${index}`} className="h-[282px]">
                  <img
                    className="h-full"
                    src={image}
                    alt={`experience-${index}`}
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
      <div className="flex flex-col gap-0.5 px-4 py-4">
        <div className=" flex gap-4 justify-start items-start">
          <Avatar size="xs" image_url={null} />
          <h2 className="font-bold text-lg leading-5">
            <p>Biaka Biodescanso</p>
            <p>Cali, Colombia</p>
          </h2>
        </div>
        <p className="text-[0.9375rem] mt-2">
          Glamping en el bosque de los farallones de Cali
        </p>
        <div className="flex justify-between">
          <ScoreStar scoreStar={3.4} type="simple" />
          <p className="text-[1.2rem]">$500.000</p>
        </div>
        <div className="flex w-full items-center my-4">
          <div className="flex h-0.5 w-full bg-gray-300"></div>
        </div>
        <p className="text-[15px] leading-5 my-4">
          Lorem ipsum dolor sit amet consectetur. Semper odio at sed tempor nec
          massa feugiat. In egestas nisl iaculis pulvinar facilisi ante in duis.
          Lobortis vivamus ultricies diam platea aliquam imperdiet. Mollis
          tellus rhoncus mauris sed facilisis lorem urna. Felis lobortis nunc
          sit aliquam egestas condimentum pellentesque sed. Sit dictum venenatis
          porta nulla habitant viverra tempor.
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
      </div>
      <div className="flex justify-center font-bold">Componente Review</div>
      {/* <div>
        <h1>Geolocation Component</h1>
        {error ? (
          <p>{error}</p>
        ) : position ? (
          <>
            <p>Latitude: {position.coords.latitude}</p>
            <p>Longitude: {position.coords.longitude}</p>
          </>
        ) : (
          <p>Loading position...</p>
        )}
      </div> */}
    </div>
  );
};
export default ExperienceDetail;
