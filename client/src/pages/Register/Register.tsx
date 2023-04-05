import { useState } from 'react';
import SelectAcountType from '../../components/SelectAcountType/SelectAcountType';
import PersonalForm from '../../components/RegisterForm/Personal';
import ProviderForm from '../../components/RegisterForm/Provider';
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
			{/* still have to modify forms */}
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
