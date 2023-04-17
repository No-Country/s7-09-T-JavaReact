import Avatar from "../../components/Avatar/Avatar"
import ScoreStar from "../../components/ScoreStar/ScoreStar"
import { Experience } from "../../models/Experiences"

const Description = ({title, averageScore, city, subtitle, description}: Experience) => {
  return (
    <div className="w-full h-full flex flex-col gap-0.5">
        <div className=" flex gap-4 justify-start items-start">
          <Avatar size="xs" image_url={null} />
          <h2 className="font-bold text-lg leading-5">
            <p>{title}</p>
            <p>{city?.city}, {city?.country}</p>
          </h2>
        </div>
        <p className="text-[0.9375rem] mt-2">
          {subtitle}
        </p>
        <div className="flex justify-between">
          <ScoreStar scoreStar={averageScore!} type="simple" />
          <p className="text-[1.2rem]">$500.000</p>
        </div>
        <div className="flex w-full items-center my-4">
          <div className="flex h-0.5 w-full bg-gray-300"></div>
        </div>
        <p className="text-[15px] leading-5 my-4">
          {description}
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
        </div>
  )
}
export default Description