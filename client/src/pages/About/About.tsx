import Banner from "/images/aboutBanner.avif";
import Review1 from "/images/UserReview.avif";
import Review2 from "/images/Review2.avif";
import Review3 from "/images/Review3.avif";
import Geolocalizacion from "../../assets/icons/Geolocalizacion.svg";
import Categorias from "../../assets/icons/Categorias.svg";
import Valoraciones from "../../assets/icons/Valoraciones.svg";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/autoplay";
import "swiper/css/pagination";
import "swiper/css/effect-fade";
import { Autoplay, EffectFade, Pagination } from "swiper";

const sliderContent = [
  {
    img: Review1,
    text: "Me encanta, logré encontrar experiencias increíbles que estaban en mi ciudad de las cuáles no sabía de su existencia.",
  },
  {
    img: Review2,
    text: "Es increíble, suelo viajar mucho y gracias a Tripmate he encontrado actividades que hacer en cada ciudad que visito.",
  },
  {
    img: Review3,
    text: "Tripmate me dio la posibilidad de pasar más tiempo junto a mi familia realizando diferentes actividades al aire libre.",
  },
];

const About = () => {
  return (
    <div className="w-full max-w-6xl mx-auto">
      <div
        id="Banner"
        className="relative h-96 md:h-[400px] "
      >
        <img
          src={Banner}
          alt="About Banner"
          className="w-full h-full filter brightness-75 object-cover object-top"
        />
        <p className="absolute top-20 left-6 w-2/3 font-bold text-white text-2xl md:text-5xl drop-shadow-[0_4px_4px_rgba(0,0,0,0.25)]">
          No pierdas la oportunidad de disfrutar eso que te gusta.
        </p>
      </div>
      <div id="body" className="m-4">
        <h2 className="font-bold text-center my-8">¿Qué es Tripmate?</h2>
        <p className="md:w-3/4 md:mx-auto">
          Tripmate es una web app diseñada para ayudar a las personas a
          encontrar experiencias y servicios de alta calidad cerca de su
          ubicación actual. La app utiliza la tecnología de geolocalización para
          detectar la ubicación del usuario y mostrarle los resultados más
          relevantes en su área.
        </p>
        <h2 className="font-bold text-center my-8">¿Qué Ofrecemos?</h2>
        <div className="md:flex md:gap-16 mx-8">
          <div className="my-8 space-y-6">
            <img
              className="mx-auto"
              alt="geolocalizacion"
              src={Geolocalizacion}
            />
            <p className="md:text-center">
              Encuentra rápidamente los mejores servicios y experiencias en tu
              área y saca el máximo provecho.
            </p>
          </div>
          <div className="my-8 space-y-6">
            <img className="mx-auto" alt="categorias" src={Categorias} />
            <p className="md:text-center">
              Selecciona entre las diferentes categorías para encontrar lo que
              más se acomode a tus necesidades.
            </p>
          </div>
          <div className="my-8 space-y-6">
            <img className="mx-auto" alt="valoraciones" src={Valoraciones} />
            <p className="md:text-center">
              Mira entre las diferentes valoraciones y reseñas de los usuarios
              para encontrar la mejor experiencia.
            </p>
          </div>
        </div>
        <div className="my-8 space-y-6">
          <h2 className="font-bold text-center my-8">
            Opiniones de nuestros clientes
          </h2>
          <Swiper
            spaceBetween={50}
            slidesPerView={1}
            autoplay={{ delay: 5000, disableOnInteraction: false }}
            pagination={{ clickable: true, bulletActiveClass: "bulletActive" }}
            effect="fade"
            className="flex h-full md:hidden"
            modules={[Autoplay, Pagination, EffectFade]}
          >
            {sliderContent.map((review) => (
              <SwiperSlide key={review.img} className="h-full my-8">
                <img className="mx-auto" alt="user review" src={review.img} />
                <p className="mx-auto text-center bg-white">“{review.text}”</p>
              </SwiperSlide>
            ))}
          </Swiper>
          <div className="hidden md:block">
            <div className="flex gap-16 mx-8">
              {sliderContent.map((review) => (
                <div key={review.img} className="my-8">
                  <img className="mx-auto" alt="user review" src={review.img} />
                  <p className="mx-auto text-center bg-white">
                    “{review.text}”
                  </p>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default About;
