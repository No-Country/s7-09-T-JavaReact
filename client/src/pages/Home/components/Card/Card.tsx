type Props = {
    name: string
    icon?: string | null
    param?: string
}

const LinkCard = ({name, icon, param}: Props) => {
  return (
    <div className="flex flex-1 flex-col items-center justify-center rounded-xl p-3 border-[1px] border-black border-opacity-25 border-solid hover:cursor-pointer">
        <span>{icon ? <img className="" src={icon} alt={name} /> : 'ICO'}</span>
        <p className="font-medium text-sm text-black">{name}</p>
    </div>
  )
}
export default LinkCard