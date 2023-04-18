import { contacts } from "./ContactData";

const Contact = () => {
  return (
    <div className="w-full h-full flex flex-col lg:items-center p-6 gap-6">
      <h2 className="font-bold text-base">Equipo s7-09-javareact</h2>
      <div className="h-[650px] grid auto-cols-[10rem] grid-rows-2 grid-flow-col gap-2 lg:gap-6 font-medium overflow-x-scroll md:overflow-hidden">
        {contacts
              ? contacts.map((contact) => (
                <a key={contact.name} href={contact.link} target="_blank" rel="noopener">
                  <div className="flex flex-col rounded-t-xl shadow-md overflow-hidden">
                    <img className="w-full bg-black h-56 object-cover object-top" src={contact.img} alt={contact.alt} />
                    <div className="w-full h-full flex flex-col gap-1 p-3 border-x-2 shadow-inner">
                      <h2 className="font-bold">{contact.name}</h2>
                      <h2>{contact.role}</h2>
                    </div>
                  </div>
              </a>
                ))
              : null}
      </div>
    </div>
  );
};
export default Contact;
