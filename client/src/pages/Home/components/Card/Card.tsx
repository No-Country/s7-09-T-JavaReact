type Props = {
    name: string
    icon?: string
    param?: string
}

const LinkCard = ({name, icon, param}: Props) => {
  return (
    <div className="flex flex-1 lg:flex-[0] flex-col items-center justify-center rounded-xl p-3 shadow-md hover:cursor-pointer">
        <span><img className="" src={icon} alt={name} /></span>
        <p className="font-medium text-sm text-black">{name}</p>
    </div>
  )
}
export default LinkCard