import { useState } from 'react';
import SelectAcountType from '../../components/SelectAcountType/SelectAcountType';
import PersonalForm from '../../components/RegisterForm/PersonalForm';
import ProviderForm from '../../components/RegisterForm/ProviderForm';
import { AcountTypeEnum } from '../../models/AcountTypeEnum';

const Register = () => {
	const [typeAcount, setTypeAcount] = useState<AcountTypeEnum>(
		AcountTypeEnum.NULL
	);
	return (
		<div>
			{typeAcount === AcountTypeEnum.NULL && (
				<SelectAcountType setType={setTypeAcount} />
			)}
			{typeAcount === AcountTypeEnum.PERSONAL && <PersonalForm />}
			{typeAcount === AcountTypeEnum.SUPPLIER && <ProviderForm />}
			{typeAcount !== AcountTypeEnum.NULL && (
				<div className='flex w-full justify-center'>
					<button
						className='font-bold'
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
