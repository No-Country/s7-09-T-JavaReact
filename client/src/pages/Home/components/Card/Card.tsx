import { useSelector } from "react-redux"
import { useGetExperiencesByCategory } from "../../../../hooks/useExperiences"
import { AppStore } from "../../../../app/store"
import { Experiences } from "../../../../models/Experiences"
import { Dispatch, SetStateAction } from "react"

type Props = {
    name: string
    icon?: string
    categoryId: number
    experiences: Experiences;
    setExperience: Dispatch<SetStateAction<Experiences>>
}

const LinkCard = ({name, icon, categoryId, setExperience, experiences }: Props) => {

  const onErrorCategory = (data: Experiences) => {
    console.log(data);
  };
  
  const {latitude, longitude} =useSelector((store: AppStore) => store.auth.position.coords)
  const onSuccess = (searchResult: Experiences) => {
    setExperience(searchResult);
  };
  const { data: listExperience, refetch: refetchCategory } = useGetExperiencesByCategory(latitude, longitude, categoryId, onSuccess,onErrorCategory);
  const handleClick = () => {
    refetchCategory()
    // console.log('click en category')
    // ;
    // setExperience(listExperience!)
    
  }


  return (
    <div onClick={handleClick} className="flex flex-1 lg:flex-[0] flex-col items-center justify-center rounded-xl p-3 shadow-md hover:cursor-pointer">
        <span><img className="" src={icon} alt={name} /></span>
        <p className="font-medium text-sm text-black">{name}</p>
    </div>
  )
}
export default LinkCard