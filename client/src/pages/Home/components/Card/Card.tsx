type Props = {
    name: string
    icon?: string
    param?: string
}

const LinkCard = ({name, icon, param}: Props) => {
  return (
    <div className="flex flex-1 flex-col items-center justify-center rounded-xl p-3 border-[1px] border-black border-opacity-25 border-solid hover:cursor-pointer">
        <span><img className="" src={icon} alt={name} /></span>
        <p className="font-medium text-sm text-black">{name}</p>
    </div>
  )
}
export default LinkCard