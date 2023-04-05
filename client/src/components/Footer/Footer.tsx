import TripmateLogo from "../../assets/icons/tripmatelogo.svg";

const Footer = () => {
  return (
    <div className="flex flex-col items-center justify-center py-6">
        <img className="w-32" src={TripmateLogo} alt="Tripmate" />
        <h2 className="text-base font-normal">Realizado por el equipo s7-09-javareact   </h2>
    </div>
  )
}
export default Footer