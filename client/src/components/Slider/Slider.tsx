import { Swiper, SwiperSlide } from 'swiper/react';
import { HomeBannerImg } from '../../models/HomeBannerImg';
import 'swiper/css';
import 'swiper/css/autoplay';
import 'swiper/css/pagination';
import 'swiper/css/effect-fade';
import '../../index.css';
import { Autoplay, EffectFade, Pagination } from 'swiper';

const Slider = () => {
	return (
		<Swiper
			spaceBetween={50}
			slidesPerView={1}
			autoplay={{ delay: 5000, disableOnInteraction: false }}
			pagination={{ clickable: true, bulletActiveClass: 'bulletActive' }}
			effect='fade'
			className='flex h-full'
			modules={[Autoplay, Pagination, EffectFade]}
		>
			{HomeBannerImg.map((image) => (
				<SwiperSlide key={image.alt} className='h-full'>
					<img
						src={image.image}
						alt={image.alt}
						className='w-full h-full md:max-h-[25rem] object-cover md:object-cover md:object-top'
					/>
					<div className='bg-black opacity-20 absolute top-0 left-0 w-full h-full z-[2]'></div>
				</SwiperSlide>
			))}
		</Swiper>
	);
};
export default Slider;
