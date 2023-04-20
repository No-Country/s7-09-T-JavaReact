import { useSelector } from "react-redux"
import { useGetExperiencesByCategory } from "../../../../hooks/useExperiences"
import { AppStore } from "../../../../app/store"
import { Experiences } from "../../../../models/Experiences"

type Props = {
    name: string
    icon?: string
    categoryId: number
}

const LinkCard = ({name, icon, categoryId, }: Props) => {

  const onErrorCategory = (data: Experiences) => {
    console.log(data);
  };
  
  const {latitude, longitude} =useSelector((store: AppStore) => store.auth.position.coords)
  const { refetch: refetchCategory } = useGetExperiencesByCategory(latitude, longitude, categoryId, onErrorCategory);

  const handleClick = () => {
    refetchCategory()
    console.log('click en category')
  }


  return (
    <div onClick={handleClick} className="flex flex-1 lg:flex-[0] flex-col items-center justify-center rounded-xl p-3 shadow-md hover:cursor-pointer">
        <span><img className="" src={icon} alt={name} /></span>
        <p className="font-medium text-sm text-black">{name}</p>
    </div>
  )
}
export default LinkCard