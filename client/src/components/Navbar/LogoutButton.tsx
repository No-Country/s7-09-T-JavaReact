import LogoutLogo from "../../assets/icons/logout.svg";

const LogoutButton = () => {
  return (
    <button className="flex gap-1 items-center font-normal text-sm">
            <img src={LogoutLogo} alt="Logout" />
            Desconectarse
    </button>
  )
}
export default LogoutButton