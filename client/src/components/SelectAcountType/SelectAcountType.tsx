import logo from "../../assets/icons/tripmatelogo.svg";
import { PublicRoutes } from "../../models/routes";
import { Link } from "react-router-dom";
import { AcountTypeEnum } from "../../models/AcountTypeEnum";

interface Props {
  setType: (type: AcountTypeEnum) => void;
}

const SelectAcountType = ({ setType }: Props) => {
  return (
    <div className="flex flex-col items-center w-full">
      <img className="w-[18.5rem] my-12" src={logo} alt="tripmate" />
      <div className="flex flex-col p-4 w-full">
        <p className="font-bold">Selecciona el tipo de cuenta</p>
        <input
          type="submit"
          value={"Cuenta personal"}
          onClick={() => setType(AcountTypeEnum.PERSONAL)}
          className="rounded-md bg-[#FF5C00] text-white font-medium w-full h-10 mt-10"
        />
        <input
          type="submit"
          value={"Soy proveedor"}
          onClick={() => setType(AcountTypeEnum.SUPPLIER)}
          className="rounded-md bg-white text-[#FF5C00] border-[#FF5C00] border-solid border-[0.0625rem] font-medium w-full h-10 mt-4"
        />
      </div>
      <div className="flex gap-2 mt-2">
        <span>Volver a </span>
        <Link to={`/${PublicRoutes.LOGIN}`}>
          <span className="text-[#FF5C00] font-bold">Inicio de sesión</span>
        </Link>
      </div>
    </div>
  );
};

export default SelectAcountType;
