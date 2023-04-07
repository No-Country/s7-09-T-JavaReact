import Banner from '../../../public/images/AboutBanner.png';
import Review from '../../../public/images/UserReview.png';
import Geolocalizacion from '../../assets/icons/Geolocalizacion.svg';
import Categorias from '../../assets/icons/Categorias.svg';
import Valoraciones from '../../assets/icons/Valoraciones.svg';

const About = () => {
	return (
		<div>
			<div id='Banner' className='relative max-w-2xl'>
				<img
					src={Banner}
					alt='About Banner'
					className='w-full filter brightness-50 '
				/>
				<p className='absolute top-1/3 left-8 w-2/3 font-bold text-white text-3xl'>
					No pierdas la oportunidad de disfrutar eso que te gusta.
				</p>
			</div>
			<div id='body' className='m-4'>
				<p>
					Tripmate es una web app diseñada para ayudar a las personas a
					encontrar experiencias y servicios de alta calidad cerca de su
					ubicación actual. La app utiliza la tecnología de geolocalización para
					detectar la ubicación del usuario y mostrarle los resultados más
					relevantes en su área.
				</p>
				<div className='my-8 space-y-6'>
					<img
						className='mx-auto'
						alt='geolocalizacion'
						src={Geolocalizacion}
					/>
					<p>
						Encuentra rápidamente los mejores servicios y experiencias en tu
						área y saca el máximo provecho.
					</p>
				</div>
				<div className='my-8 space-y-6'>
					<img className='mx-auto' alt='categorias' src={Categorias} />
					<p>
						Selecciona entre las diferentes categorías para encontrar lo que más
						se acomode a tus necesidades.
					</p>
				</div>
				<div className='my-8 space-y-6'>
					<img className='mx-auto' alt='valoraciones' src={Valoraciones} />
					<p>
						Mira entre las diferentes valoraciones y reseñas de los usuarios
						para encontrar la mejor experiencia.
					</p>
				</div>
				<div className='my-8 space-y-6'>
					<img className='mx-auto' alt='user review' src={Review} />
					<p className='mx-auto text-center'>
						“Me encanta, logré encontrar experiencias increíbles que estaban en
						mi ciudad de las cuáles no sabía de su existencia.”
					</p>
				</div>
			</div>
		</div>
	);
};
export default About;
