import { useState } from "react";
import SelectAcountType from "../../components/SelectAcountType/SelectAcountType";
import { AcountTypeEnum } from "../../models/AcountTypeEnum";

const Register = () => {
  const [typeAcount, setTypeAcount] = useState<AcountTypeEnum>(
    AcountTypeEnum.NULL
  );
  return (
    <div>
      {typeAcount === AcountTypeEnum.NULL && (
        <SelectAcountType setType={setTypeAcount} />
      )}
      {typeAcount === AcountTypeEnum.PERSONAL && (
        <div className="font-bold text-lg text-center my-10">
          Formulario de Registro Personal
        </div>
      )}
      {typeAcount === AcountTypeEnum.SUPPLIER && (
        <div className="font-bold text-lg text-center my-10">
          Formulario de Registro Proveedor
        </div>
      )}
      {typeAcount !== AcountTypeEnum.NULL && (
        <div className="flex w-full justify-center">
          <button
            className="font-bold"
            onClick={() => setTypeAcount(AcountTypeEnum.NULL)}
          >
            Volver
          </button>
        </div>
      )}
    </div>
  );
};
export default Register;
