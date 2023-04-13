import { contacts } from "./ContactData";

const Contact = () => {
  return (
    <div className="w-full h-full flex flex-col lg:items-center p-6 gap-6">
      <h2 className="font-bold text-base">Equipo s7-09-javareact</h2>
      <div className="h-[550px] grid auto-cols-[10rem] grid-rows-2 grid-flow-col gap-2 lg:gap-6 font-medium overflow-x-scroll">
        {contacts
              ? contacts.map((contact) => (
                <div className="min-w-[10rem] h-64 flex flex-col flex-grow flex-shrink-0 rounded-t-xl shadow-md overflow-hidden">
                {contact.img ? <img className="min-w-[10rem] h-full" src={contact.img} alt={contact.name} /> : <div className="min-w-[10rem] h-full bg-[#D9D9D9]"></div>}
                <div className="flex flex-col gap-1 p-3 border-x-2 shadow-inner">
                  <h2 className="font-bold">{contact.name}</h2>
                  <h2>{contact.role}</h2>
                </div>
              </div>
                ))
              : null}
      </div>
    </div>
  );
};
export default Contact;
